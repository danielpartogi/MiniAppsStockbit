package com.apelgigit.data

import com.apelgigit.commons.utils.Resource
import com.apelgigit.data.locale.dao.SubsCryptoDao
import com.apelgigit.data.model.request.CryptoRequest
import com.apelgigit.data.model.response.CryptoResponse
import com.apelgigit.data.remote.services.CryptoService
import com.apelgigit.data.repository.CryptoRepositoryImpl
import com.apelgigit.data.websocket.services.CryptoWSService
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test


class CryptoRepositoryTest {

    @RelaxedMockK
    private lateinit var api: CryptoService

    @RelaxedMockK
    private lateinit var ws: CryptoWSService

    @RelaxedMockK
    private lateinit var dao: SubsCryptoDao

    private lateinit var repo: CryptoRepositoryImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        repo = CryptoRepositoryImpl(api, ws, dao)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun given_success_data_return_data() {
        //Given
        val successResponse: CryptoResponse = mockk()

        coEvery { api.getCryptoData(0,0,"ASD") } returns successResponse

        runBlockingTest {
            // When
            val result = repo.getCryptoList(CryptoRequest(0,10))
            // Then
           print(result)
        }
    }
}
