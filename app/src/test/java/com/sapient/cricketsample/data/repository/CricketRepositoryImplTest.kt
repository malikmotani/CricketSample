package com.sapient.cricketsample.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sapient.cricketsample.common.CoroutineTestRule
import com.sapient.cricketsample.common.ResponseUtil
import com.sapient.cricketsample.domain.repository.CricketRepository
import com.sapient.cricketsample.domain.source.CricketRemoteDataSource
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CricketRepositoryImplTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    private lateinit var cricketRepository: CricketRepository
    private val cricketRemoteDataSource = mockk<CricketRemoteDataSource>(relaxed = true)

    @Before
    fun setup() {
        cricketRepository = CricketRepositoryImpl(cricketRemoteDataSource)
    }

    @Test
    fun test_getMatches() = runTest {
        val successResponse = ResponseUtil.stubSuccessResponse()
        coEvery {
            cricketRemoteDataSource.fetchMatches()
        } returns successResponse

        TestCase.assertNotNull(cricketRepository.getMatches())
    }
}