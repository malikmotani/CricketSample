package com.sapient.cricketsample.domain.repository

import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.NetworkCallResult

interface CricketRepository {
    suspend fun getMatches(): NetworkCallResult<List<MatchItem>>
}