package com.apelgigit.data.locale.dao

import androidx.room.*
import com.apelgigit.data.websocket.response.CryptoWSResponse
import kotlinx.coroutines.flow.Flow

@Dao
interface SubsCryptoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(response: CryptoWSResponse)

    @Query("SELECT * FROM crypto_subs ORDER BY symbol")
    fun getAll(): Flow<List<CryptoWSResponse>>

    @Query("DELETE FROM crypto_subs WHERE symbol == :symbol")
    fun remove(symbol: String)
}