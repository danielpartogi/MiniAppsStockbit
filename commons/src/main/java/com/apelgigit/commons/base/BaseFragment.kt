package com.apelgigit.commons.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.apelgigit.common_ui.R
import com.apelgigit.commons.ConnectionLiveData
import com.apelgigit.commons.isConnected
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass

abstract class BaseFragment<VM : BaseViewModel>(clazz: KClass<VM>) : Fragment() {

    protected lateinit var connectionLiveData: ConnectionLiveData

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
        observeConnectionLiveData()
        observeVm()
        onCreated(view)
    }

    private fun observeConnectionLiveData() {
        connectionLiveData.observe(this.viewLifecycleOwner, Observer {
            viewModel.isNetworkAvailable.value = it
        })
        viewModel.isNetworkAvailable.value = activity?.isConnected
    }


    private fun observeVm() {

        viewModel.errorEvent.observe(this.viewLifecycleOwner, Observer { errorEvent ->
            errorEvent.getContentIfNotHandled()?.let {
                when (viewModel.errorType) {
                    ErrorType.ALERT_DIALOG -> this.showErrorDialog(
                        "Error",
                        it.message ?: "Unexpected Error"
                    )
                    ErrorType.SNACKBAR -> this.setupSnackBar(it.message)
                }
            }
        })

    }

    private fun showErrorDialog(title: String, message: String) {
        val builder = AlertDialog.Builder(requireContext())
            .setTitle(title)
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