package com.apelgigit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apelgigit.data.model.Crypto
import com.apelgigit.home.R
import com.apelgigit.home.databinding.ItemCryptoBinding

class CryptoListAdapter(
) : ListAdapter<Crypto, CryptoListViewHolder>(CryptoItemDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoListViewHolder {
        val binding =
            ItemCryptoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CryptoListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class CryptoItemDiffCallback : DiffUtil.ItemCallback<Crypto>() {
        override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }
    }
}
