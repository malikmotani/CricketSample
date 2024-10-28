package com.sapient.cricketsample.common

import com.sapient.cricketsample.data.model.CricketMatchResponse
import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.NetworkCallResult
import com.sapient.cricketsample.data.model.ScoreItem
import com.sapient.cricketsample.data.model.TeamInfoItem

object ResponseUtil {

     fun matchList(): List<MatchItem> {
        return listOf(
            MatchItem(
                id = "2a1599ff-be09-43ff-9f48-18673726222b",
                seriesId = "82f964ef-315f-4a07-b01d-862c09dcb852",
                name = "Kuwait Women vs Myanmar Women, 5th Match",
                matchType = "t20",
                status = "Myanmar Women won by 11 runs",
                venue = "Singapore National Cricket Ground, Singapore",
                date = "2024-10-25",
                teams = listOf("Kuwait Women", "Myanmar Women"),
                bbbEnabled = true,
                hasSquad = true,
                matchStarted = true,
                matchEnded = true,
                dateTimeGMT = "2024-10-25T07:00:00",
                fantasyEnabled = false,
                teamInfo = listOf(
                    TeamInfoItem(
                        name = "Gambia",
                        shortname = "GAM",
                        img = "https://h.cricapi.com/img/icon512.png"
                    ),
                    TeamInfoItem(
                        name = "Mozambique",
                        shortname = "MOZ",
                        img = "https://h.cricapi.com/img/icon512.png"
                    )
                ),
                score = listOf(
                    ScoreItem(r = "121", w = "4", o = "20", inning = "Myanmar Women Inning 1"),
                    ScoreItem(r = "110", w = "7", o = "20", inning = "Kuwait Women Inning 1")
                )
            ),
            MatchItem(
                id = "2a1599ff-be09-43ff-9f48-18673726222c",
                seriesId = "82f964ef-315f-4a07-b01d-862c09dcb852",
                name = "Kuwait Women vs Myanmar Women, 6th Match",
                matchType = "t20",
                status = "Kuwait Women won by 10 runs",
                venue = "Singapore National Cricket Ground, Singapore",
                date = "2024-10-27",
                teams = listOf("Kuwait Women", "Myanmar Women"),
                bbbEnabled = true,
                hasSquad = true,
                matchStarted = true,
                matchEnded = true,
                dateTimeGMT = "2024-10-27T07:00:00",
                fantasyEnabled = false,
                teamInfo = listOf(
                    TeamInfoItem(
                        name = "Gambia",
                        shortname = "GAM",
                        img = "https://h.cricapi.com/img/icon512.png"
                    ),
                    TeamInfoItem(
                        name = "Mozambique",
                        shortname = "MOZ",
                        img = "https://h.cricapi.com/img/icon512.png"
                    )
                ),
                score = listOf(
                    ScoreItem(r = "121", w = "4", o = "20", inning = "Myanmar Women Inning 1"),
                    ScoreItem(r = "110", w = "7", o = "20", inning = "Kuwait Women Inning 1")
                )
            )
        )
    }

    fun stubSuccessResponse(): NetworkCallResult.Success<List<MatchItem>> {
        return NetworkCallResult.Success(matchList())
    }

    fun stubErrorResponse(): NetworkCallResult.Error {
        return NetworkCallResult.Error(Throwable("Unknown error"))
    }

    fun stubCricketMatchSuccessResponse(): CricketMatchResponse {
        return CricketMatchResponse(
            apikey = "123",
            status = "success",
            data = matchList()
        )
    }
}