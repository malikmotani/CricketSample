package com.sapient.cricketsample.data.source

import com.sapient.cricketsample.common.CoroutineTestRule
import com.sapient.cricketsample.common.ResponseUtil
import com.sapient.cricketsample.data.model.NetworkCallResult
import com.sapient.cricketsample.data.service.CricketService
import com.sapient.cricketsample.domain.source.CricketRemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.internal.wait
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CricketRemoteDataSourceImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var cricketRemoteDataSource: CricketRemoteDataSource
    private val cricketService = mockk<CricketService>(relaxed = true)

    @Before
    fun setup() {
        cricketRemoteDataSource = CricketRemoteDataSourceImpl(cricketService)
    }

    @Test
    fun test_getMatches() = runTest {
        val successResponse = ResponseUtil.stubCricketMatchSuccessResponse()
        coEvery {
            cricketService.getMatches()
        } returns successResponse

        TestCase.assertEquals(NetworkCallResult.Success(successResponse.data), cricketRemoteDataSource.fetchMatches())
    }
}