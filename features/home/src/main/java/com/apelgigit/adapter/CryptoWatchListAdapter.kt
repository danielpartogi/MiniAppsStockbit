package com.apelgigit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apelgigit.data.model.Crypto
import com.apelgigit.data.websocket.response.CryptoWSResponse
import com.apelgigit.home.databinding.ItemCryptoBinding
import com.apelgigit.listener.CryptoListener

class CryptoWatchListAdapter(
   private val listener: CryptoListener
) : ListAdapter<CryptoWSResponse, CryptoWatchListViewHolder>(CryptoWSItemDiffCallback()) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoWatchListViewHolder {
        val binding =
            ItemCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoWatchListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoWatchListViewHolder, position: Int) {
        holder.bind(getItem(position))
        holder.itemView.setOnLongClickListener {
            listener.onClickCryptoList(getItem(position).symbol)
            true
        }
    }

    class CryptoWSItemDiffCallback : DiffUtil.ItemCallback<CryptoWSResponse>() {
        override fun areItemsTheSame(oldItem: CryptoWSResponse, newItem: CryptoWSResponse): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: CryptoWSResponse, newItem: CryptoWSResponse): Boolean {
            return oldItem == newItem
        }
    }


}
