package com.sapient.cricketsample.common

import com.sapient.cricketsample.BuildConfig

object AppConstants {
    const val BASE_URL = "https://api.cricapi.com/"
    const val CURRENT_MATCH = "v1/currentMatches/?apikey=${BuildConfig.API_KEY}"
}