package com.apelgigit.data.model

import com.google.gson.annotations.SerializedName

data class RawCryptoDetail(
    @SerializedName("CHANGE24HOUR")
    val change24Hour: Double = 0.0,
    @SerializedName("CHANGEDAY")
    val changeDay: Double = 0.0,
    @SerializedName("CHANGEHOUR")
    val changeHour: Double = 0.0,
    @SerializedName("CHANGEPCT24HOUR")
    val changePCT24Hour: Double = 0.0,
    @SerializedName("CHANGEPCTDAY")
    val changePCTDay: Double = 0.0,
    @SerializedName("CHANGEPCTHOUR")
    val changePCTHour: Double = 0.0,
    @SerializedName("CONVERSIONSYMBOL")
    val conversionSymbol: String = "",
    @SerializedName("CONVERSIONTYPE")
    val conversionType: String = "",
    @SerializedName("FLAGS")
    val flags: String = "",
    @SerializedName("FROMSYMBOL")
    val fromSymbol: String = "",
    @SerializedName("HIGH24HOUR")
    val high24Hour: Double = 0.0,
    @SerializedName("HIGHDAY")
    val highDay: Double = 0.0,
    @SerializedName("HIGHHOUR")
    val highHour: Double = 0.0,
    @SerializedName("IMAGEURL")
    val imageUrl: String = "",
    @SerializedName("LASTMARKET")
    val lastMarket: String = "",
    @SerializedName("LASTTRADEID")
    val lastTradeId: String = "",
    @SerializedName("LASTUPDATE")
    val lastUpdate: Double = 0.0,
    @SerializedName("LASTVOLUME")
    val lastVolume: Double = 0.0,
    @SerializedName("LASTVOLUMETO")
    val lastVolumeTo: Double = 0.0,
    @SerializedName("LOW24HOUR")
    val low24Hour: Double = 0.0,
    @SerializedName("LOWDAY")
    val lowDay: Double = 0.0,
    @SerializedName("LOWHOUR")
    val lowHour: Double = 0.0,
    @SerializedName("MARKET")
    val market: String = "",
    @SerializedName("MEDIAN")
    val median: Double = 0.0,
    @SerializedName("MKTCAP")
    val marketCap: Double = 0.0,
    @SerializedName("OPEN24HOUR")
    val open24Hour: Double = 0.0,
    @SerializedName("OPENDAY")
    val openDay: Double = 0.0,
    @SerializedName("OPENHOUR")
    val openHour: Double = 0.0,
    @SerializedName("PRICE")
    val price: Double = 0.0,
    @SerializedName("SUPPLY")
    val supply: Double = 0.0,
    @SerializedName("TOPTIERVOLUME24HOUR")
    val topTierVolume24Hour: Double = 0.0,
    @SerializedName("TOPTIERVOLUME24HOURTO")
    val topTierVolume24HourTo: Double = 0.0,
    @SerializedName("TOSYMBOL")
    val toSymbol: String = "",
    @SerializedName("TOTALTOPTIERVOLUME24H")
    val totaltopTierVolume24H: Double = 0.0,
    @SerializedName("TOTALtoPTIERVOLUME24Hto")
    val totaltopTierVolume24HTo: Double = 0.0,
    @SerializedName("TOTALVOLUME24H")
    val totalVolume24H: Double = 0.0,
    @SerializedName("TOTALVOLUME24HTO")
    val totalVolume24HTo: Double = 0.0,
    @SerializedName("TYPE")
    val type: String = "",
    @SerializedName("VOLUME24HOUR")
    val volume24Hour: Double = 0.0,
    @SerializedName("VOLUME24HOURTO")
    val volume24HourTo: Double = 0.0,
    @SerializedName("VOLUMEDAY")
    val volumeDay: Double = 0.0,
    @SerializedName("VOLUMEDAYTO")
    val volumeDayTo: Double = 0.0,
    @SerializedName("VOLUMEHOUR")
    val volumeHour: Double = 0.0,
    @SerializedName("VOLUMEHOURTO")
    val volumeHourTo: Double = 0.0
)