package com.apelgigit.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.apelgigit.data.locale.dao.SubsCryptoDao
import com.apelgigit.data.websocket.response.CryptoWSResponse

@Database(
    entities = [
        CryptoWSResponse::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun subsCryptoDao(): SubsCryptoDao

}