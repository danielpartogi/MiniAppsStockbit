package com.apelgigit.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.apelgigit.commons.base.BaseFragment
import com.apelgigit.commons.ext.observeValue
import com.apelgigit.login.databinding.FragmentLoginBinding

class LoginFragment: BaseFragment<LoginViewModel>(LoginViewModel::class) {

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
        observeValue(viewModel.foo){
           Log.d("asd", it.toString())
        }
    }
}