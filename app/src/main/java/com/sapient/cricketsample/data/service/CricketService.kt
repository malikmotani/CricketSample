package com.sapient.cricketsample.data.service

import com.sapient.cricketsample.common.AppConstants
import com.sapient.cricketsample.data.model.CricketMatchResponse
import retrofit2.http.GET

interface CricketService {
    @GET(AppConstants.CURRENT_MATCH)
    suspend fun getMatches(): CricketMatchResponse
}