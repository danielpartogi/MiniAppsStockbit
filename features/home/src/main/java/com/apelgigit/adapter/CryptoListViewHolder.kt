package com.apelgigit.adapter

import android.content.Context
import android.graphics.Color.red
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.apelgigit.data.model.Crypto
import com.apelgigit.home.R
import com.apelgigit.home.databinding.ItemCryptoBinding
import java.math.RoundingMode
import java.text.DecimalFormat

class CryptoListViewHolder(private val binding: ItemCryptoBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(cryptoModel: Crypto) {
        val coinInfo = cryptoModel.coinInfo
        val rawData = cryptoModel.raw.rawDetail
        with(binding) {
            tvCode.text = coinInfo.name
            tvCompanyName.text = coinInfo.fullName
            tvPriceChange.changeTextColor(rawData.change24Hour, itemView.context)
            tvPrice.text = cryptoModel.display.displayDetail.price
            val changePercentage = rawData.changePCT24Hour.roundDecimal(2).addPrefix()
            val changePrice = rawData.change24Hour.roundDecimal(2).addPrefix()

            tvPriceChange.text =
                itemView.context.getString(R.string.price_change, changePrice, changePercentage)
        }
    }
}

fun TextView.changeTextColor(changesPrice: Double, context: Context) {
    if (changesPrice < 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.red))
    } else if (changesPrice > 0) {
        this.setTextColor(ContextCompat.getColor(context, R.color.green))
    }
}

fun Double.roundDecimal(digit: Int) = "%.${digit}f".format(this)

fun String.addPrefix(): String {
    val value = this.replace(",", ".").toDouble()
    return when {
        value < 0 -> {
            "-$value"
        }
        value > 0 -> {
            "+$value"
        }
        else -> this
    }
}