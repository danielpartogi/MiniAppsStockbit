package com.apelgigit.home

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.apelgigit.adapter.CryptoListAdapter
import com.apelgigit.commons.EndlessRecyclerOnScrollListener
import com.apelgigit.commons.base.BaseFragment
import com.apelgigit.commons.base.ErrorType
import com.apelgigit.commons.ext.RequestStatus
import com.apelgigit.commons.ext.Resource
import com.apelgigit.commons.ext.observeValue
import com.apelgigit.data.model.Crypto
import com.apelgigit.home.databinding.FragmentWatchListBinding
import com.apelgigit.listener.CryptoListener

class WatchListFragment: BaseFragment<WatchListViewModel>(WatchListViewModel::class) {

    private lateinit var binding: FragmentWatchListBinding
    private lateinit var adapter: CryptoListAdapter
    private var layoutManager: LinearLayoutManager? = null

    private val scrollListener by lazy {
        object : EndlessRecyclerOnScrollListener(layoutManager) {
            override fun onLoadMore(currentPage: Int) {
                if (!binding.swrWatchList.isRefreshing)
                    viewModel.fetchData(currentPage)
            }
        }
    }

        override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreated(view: View) {

        adapter = CryptoListAdapter(object : CryptoListener {
            override fun onClickCryptoList(symbol: String) {
                showAlertAddCrypto(symbol)
            }

        }
        )
        binding.rvWatchList.apply {
            this.adapter = this@WatchListFragment.adapter
            this@WatchListFragment.layoutManager = layoutManager as? LinearLayoutManager
            this.addOnScrollListener(scrollListener)
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        binding.swrWatchList.setOnRefreshListener {
            refresh()
        }

        viewModel.fetchData(0)

        observe()
    }

    private fun observe(){
        with(viewModel){
            observeValue(cryptoDataList){
                setCryptoData(it)
            }
        }
    }

    private fun setCryptoData(data: Resource<List<Crypto>>){

        when(data.requestStatus){
            RequestStatus.EMPTY -> {
                binding.swrWatchList.isRefreshing = false
            }

            RequestStatus.SUCCESS -> {
                binding.swrWatchList.isRefreshing = false
                adapter.submitList(data.data)
            }
            RequestStatus.LOADING -> {
                adapter.submitList(data.data)
            }

            RequestStatus.ERROR -> {
                binding.swrWatchList.isRefreshing = false
                viewModel.setupError(ErrorType.SNACKBAR, data.message)
            }
        }
    }

    private fun refresh(){
        binding.swrWatchList.isRefreshing = true
        viewModel.fetchData(0)
        scrollListener.resetState()
        if (isNetworkAvailable)
            adapter.submitList(null)
    }

    private fun showAlertAddCrypto(symbol: String) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Watch Crypto Volume")
            .setMessage("Apakah anda yakin untuk stream volume $symbol?")
            .setPositiveButton("OK") { _, _ ->
            viewModel.setWatchList(symbol)
        }
        .setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }
            .create()

        dialog.show()
    }
}