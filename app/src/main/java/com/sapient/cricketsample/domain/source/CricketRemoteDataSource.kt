package com.sapient.cricketsample.domain.source

import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.NetworkCallResult

interface CricketRemoteDataSource {
    suspend fun fetchMatches(): NetworkCallResult<List<MatchItem>>
}