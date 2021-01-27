package com.apelgigit.adapter

import androidx.recyclerview.widget.RecyclerView
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.websocket.response.CryptoWSResponse
import com.apelgigit.home.R
import com.apelgigit.home.databinding.ItemCryptoBinding

class CryptoWatchListViewHolder(private val binding: ItemCryptoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(cryptoModel: CryptoWSResponse) {
        with(binding) {
            tvCode.text = cryptoModel.symbol
            tvPrice.text = cryptoModel.topTierFullVolume.toString()
        }
    }
}