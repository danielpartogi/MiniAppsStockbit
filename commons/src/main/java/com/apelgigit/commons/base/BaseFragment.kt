package com.apelgigit.commons.base

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.apelgigit.commons.ConnectionLiveData
import com.apelgigit.commons.R
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseFragment<VM : BaseViewModel>(clazz: KClass<VM>) : Fragment() {

    protected lateinit var connectionLiveData: ConnectionLiveData
    protected lateinit var navigationController: NavController
    var isNetworkAvailable: Boolean = false

    /**
     * The viewModel acts upon the model and the view.
     * It retrieves data from repositories (the model),
     * and formats it for display in the view.
     */
    protected val viewModel: VM by viewModel(clazz)

    protected abstract fun onCreated(view: View)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        connectionLiveData = ConnectionLiveData(requireContext())
        navigationController = findNavController()
        observeConnectionLiveData()
        observeVm()
        onCreated(view)
    }

    private fun observeConnectionLiveData() {
        connectionLiveData.observe(this.viewLifecycleOwner, {
            viewModel.isNetworkAvailable.value = it
        })
        viewModel.isNetworkAvailable.observe(this.viewLifecycleOwner, {
            isNetworkAvailable = it
            if (!it) showSnackbar(
                getString(R.string.error_network_not_available),
                Snackbar.LENGTH_LONG
            )
        })
    }

    private fun observeVm() {

        viewModel.errorEvent.observe(this.viewLifecycleOwner, { errorEvent ->
            errorEvent.getContentIfNotHandled()?.let {
                when (viewModel.errorType) {
                    ErrorType.ALERT_DIALOG -> this.showErrorDialog(
                        it.message ?: "Unexpected Error"
                    )
                    ErrorType.SNACKBAR -> this.setupSnackBar(it.message)
                }
            }
        })

    }

    open fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        //Find the currently focused view, so we can grab the correct window token from it.
        var view = activity.currentFocus
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showErrorDialog(message: String) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(getString(R.string.error))
            .setMessage(message)
            .setCancelable(true)
            .setPositiveButton(
                R.string.yes
            ) { dialog, _ ->
                dialog.dismiss()
            }
        val dialogs = builder.create()
        dialogs.show()
    }

    private fun setupSnackBar(
        snackBarEvent: String?
    ) {
        context?.let { showSnackbar(snackBarEvent ?: "Unexpected error", Snackbar.LENGTH_LONG) }
    }

    fun showSnackbar(snackBarText: String, timeLength: Int) {
        activity?.let {
            Snackbar.make(
                it.findViewById(android.R.id.content),
                snackBarText,
                timeLength
            ).show()
        }
    }
}