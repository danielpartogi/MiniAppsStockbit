package com.apelgigit.home

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import com.apelgigit.adapter.CryptoWatchListAdapter
import com.apelgigit.commons.base.BaseFragment
import com.apelgigit.commons.ext.observeValue
import com.apelgigit.home.databinding.FragmentWatchListPriceBinding
import com.apelgigit.listener.CryptoListener

class WatchListVolumeFragment: BaseFragment<WatchListVolumeViewModel>(WatchListVolumeViewModel::class) {

    private lateinit var binding: FragmentWatchListPriceBinding
    private lateinit var adapter: CryptoWatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListPriceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreated(view: View) {

        observeValue(viewModel.cryptoSubsData){

            adapter.submitList(it)
        }

        observeValue(viewModel.rawWsData){
            Log.i("ws:data", it.toString())
        }

        binding.rvWatchList.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
        adapter = CryptoWatchListAdapter(object : CryptoListener {
            override fun onClickCryptoList(symbol: String) {
                showAlertAddCrypto(symbol)
            }

        })
        viewModel.observeWS()

        binding.rvWatchList.adapter = adapter
        binding.rvWatchList.itemAnimator = null
    }

    private fun showAlertAddCrypto(symbol: String) {
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Watch Crypto Volume")
            .setMessage("Apakah anda yakin untuk stream volume $symbol?")
            .setPositiveButton("OK") { _, _ ->
                viewModel.deleteWatchList(symbol)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

}