package com.apelgigit.data.locale.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apelgigit.data.websocket.response.CryptoWSResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface SubsCryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(otpExpired: CryptoWSResponse)

    @Query("SELECT * FROM crypto_subs")
    fun getAll(): Flow<List<CryptoWSResponse>>
}