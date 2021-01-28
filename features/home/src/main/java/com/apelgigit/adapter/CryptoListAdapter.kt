package com.apelgigit.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.apelgigit.data.model.Crypto
import com.apelgigit.home.databinding.ItemCryptoBinding
import com.apelgigit.home.databinding.ItemProgressBinding
import com.apelgigit.listener.CryptoListener

class CryptoListAdapter(
    val cryptoListener: CryptoListener
) : ListAdapter<Crypto, RecyclerView.ViewHolder>(CryptoItemDiffCallback()) {

    private var isLoading = true

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            CRYPTO_DATA -> CryptoListViewHolder(
                ItemCryptoBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
            else -> LoadingHolder(
                ItemProgressBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            CRYPTO_DATA -> {
                holder as CryptoListViewHolder
                holder.bind(getItem(position))
                holder.itemView.setOnLongClickListener {

                    cryptoListener.onClickCryptoList(getItem(position).raw.rawDetail.fromSymbol)
                    true
                }
            }
            else -> {
            }
        }
    }

    class CryptoItemDiffCallback : DiffUtil.ItemCallback<Crypto>() {
        override fun areItemsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Crypto, newItem: Crypto): Boolean {
            return oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (isLoading) 1 else 0
    }

    fun hideLoading() {
        isLoading = false
        notifyItemRemoved(itemCount-1)
    }

    override fun getItemViewType(position: Int): Int {

        return if (isLoading && position == itemCount - 1) {
            LOADING
        } else{
            CRYPTO_DATA
        }
    }

    companion object {
        const val CRYPTO_DATA = 0
        const val LOADING = 1
    }
}
