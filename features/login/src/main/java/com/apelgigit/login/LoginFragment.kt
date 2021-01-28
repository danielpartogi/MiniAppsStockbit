package com.apelgigit.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.apelgigit.commons.base.BaseFragment
import com.apelgigit.commons.constants.Constants
import com.apelgigit.commons.ext.clickWithDebounce
import com.apelgigit.commons.ext.gone
import com.apelgigit.commons.ext.isMoreThan
import com.apelgigit.commons.ext.isValidUsernameAndEmail
import com.apelgigit.login.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : BaseFragment<LoginViewModel>(LoginViewModel::class) {

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreated(view: View) {
        setupView()
        setupListener()
        initObserver()
    }

    private fun setupView() {
        binding.layoutToolbar.toolbarTitle.text = getString(R.string.title_enter)
        binding.layoutToolbar.rightButton.gone()
        binding.layoutToolbar.leftButton.gone()
        binding.buttonLogin.clickWithDebounce {
            viewModel.setIsLogin()
            hideKeyboard(requireActivity())
            navigationController.navigate(LoginFragmentDirections.actionLoginFragmentToCryptoList())
        }
    }

    private fun setupListener() {
        binding.editTextEmail.addTextChangedListener {
            val isError = !it.toString().isValidUsernameAndEmail()
            binding.textInputLayoutEmail.isErrorEnabled = isError
            if (isError){
                binding.textInputLayoutEmail.error = getString(R.string.email_not_valid)
            }
            else {
                binding.textInputLayoutEmail.error = ""
            }
            viewModel.setEmail(it.toString())
        }
        binding.editTextPassword.addTextChangedListener {
            val isError = !it.toString().isMoreThan(Constants.MININIMUM_PASSWORD)
            binding.textInputLayoutEmail.isErrorEnabled = isError
            if (isError){
                binding.textInputLayoutPassword.error =
                    getString(R.string.password_not_valid, Constants.MININIMUM_PASSWORD)
            }
            else {
                binding.textInputLayoutPassword.error = ""
            }
            viewModel.setPassword(it.toString())
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.isSubmitEnabled.collect { value ->
                binding.buttonLogin.isEnabled = value
            }
        }
    }
}

