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
import com.apelgigit.commons.utils.observeValue
import com.apelgigit.home.databinding.FragmentWatchListVolumeBinding
import com.apelgigit.listener.CryptoListener

class WatchListVolumeFragment(private val delegate: HomeFragmentDelegate):
    BaseFragment<WatchListVolumeViewModel>(WatchListVolumeViewModel::class) {

    private lateinit var binding: FragmentWatchListVolumeBinding
    private lateinit var adapter: CryptoWatchListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWatchListVolumeBinding.inflate(inflater, container, false)
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
            .setTitle(getString(R.string.watch_crypto_volume))
            .setMessage(getString(R.string.question_delete_volume, symbol))
            .setPositiveButton(getString(R.string.OK)) { _, _ ->
                viewModel.deleteWatchList(symbol)
            }
            .setNegativeButton(getString(R.string.Cancel)) { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    override fun onResume() {
        super.onResume()
        delegate.updateToolbarTitle(getString(R.string.watch_crypto_volume))
    }

}