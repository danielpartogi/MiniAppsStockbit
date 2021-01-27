package com.apelgigit.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.apelgigit.adapter.CryptoListAdapter
import com.apelgigit.commons.base.BaseFragment
import com.apelgigit.commons.ext.observeValue
import com.apelgigit.home.databinding.FragmentHomeBinding

class HomeFragment: BaseFragment<HomeViewModel>(HomeViewModel::class) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: CryptoListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreated(view: View) {

        observeValue(viewModel.foo){

            Log.d("ws", it.toString())
        }

        binding.rvWatchList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = CryptoListAdapter()

        binding.rvWatchList.adapter = adapter
    }




}