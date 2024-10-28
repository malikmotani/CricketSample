package com.sapient.cricketsample.ui.match

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sapient.cricketsample.common.CoroutineTestRule
import com.sapient.cricketsample.common.ResponseUtil
import com.sapient.cricketsample.domain.repository.CricketRepository
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.unmockkAll
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CricketMatchViewModelTest {
    private lateinit var viewModel: CricketMatchViewModel
    private val cricketRepository = mockk<CricketRepository>(relaxed = true)

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @Before
    fun setup() {
        viewModel = CricketMatchViewModel(cricketRepository)
    }

    @After
    fun tearDown() {
        unmockkAll()
        clearAllMocks()
    }

    @Test
    fun test_fetchMatches_success() = runTest {
        val successResponse = ResponseUtil.stubSuccessResponse()
        coEvery {
            cricketRepository.getMatches()
        } returns successResponse

        viewModel.fetchMatches()
        coroutineTestRule.scheduler.advanceUntilIdle()

        TestCase.assertEquals(successResponse, viewModel.matchLiveData.value)
    }

    @Test
    fun test_fetchMatches_error() = runTest {
        val errorResponse = ResponseUtil.stubErrorResponse()
        coEvery {
            cricketRepository.getMatches()
        } returns errorResponse

        viewModel.fetchMatches()
        coroutineTestRule.scheduler.advanceUntilIdle()

        TestCase.assertEquals(errorResponse, viewModel.matchLiveData.value)
    }

    @Test
    fun test_fetchMatchById() {
        val successResponse = ResponseUtil.stubSuccessResponse()
        coEvery {
            cricketRepository.getMatches()
        } returns successResponse

        viewModel.fetchMatches()
        coroutineTestRule.scheduler.advanceUntilIdle()

        viewModel.fetchMatchById("2a1599ff-be09-43ff-9f48-18673726222c")
        coroutineTestRule.scheduler.advanceUntilIdle()

        TestCase.assertEquals(ResponseUtil.matchList()[1], viewModel.matchDetailLiveData.value)

    }
}