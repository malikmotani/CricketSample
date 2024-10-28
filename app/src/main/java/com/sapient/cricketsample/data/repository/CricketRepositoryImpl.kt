package com.sapient.cricketsample.data.repository

import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.NetworkCallResult
import com.sapient.cricketsample.domain.source.CricketRemoteDataSource
import com.sapient.cricketsample.domain.repository.CricketRepository
import javax.inject.Inject

class CricketRepositoryImpl @Inject constructor(
    private val cricketRemoteDataSource: CricketRemoteDataSource,
) : CricketRepository {

    override suspend fun getMatches(): NetworkCallResult<List<MatchItem>> {
        return cricketRemoteDataSource.fetchMatches()
    }

}