package com.sapient.cricketsample.data.source

import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.NetworkCallResult
import com.sapient.cricketsample.data.service.CricketService
import com.sapient.cricketsample.domain.source.CricketRemoteDataSource
import javax.inject.Inject

class CricketRemoteDataSourceImpl @Inject constructor(
    private val cricketService: CricketService,
) : CricketRemoteDataSource {

    override suspend fun fetchMatches(): NetworkCallResult<List<MatchItem>> {
        val response = cricketService.getMatches()
        return if (response.status == "success" && !response.data.isNullOrEmpty()) {
            NetworkCallResult.Success(response.data)
        } else {
            //TODO: Specific exceptions to be handled here
            NetworkCallResult.Error(Throwable("Unknown error"))
        }
    }

}