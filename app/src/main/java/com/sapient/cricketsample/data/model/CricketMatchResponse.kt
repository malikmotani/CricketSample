package com.sapient.cricketsample.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CricketMatchResponse(
    val apikey: String,
    val data: List<MatchItem>? = null,
    val status: String? = null,
) : Parcelable

@Parcelize
data class ScoreItem(
    val r: String,
    val w: String,
    val inning: String,
    val o: String,
) : Parcelable

@Parcelize
data class TeamInfoItem(
    val img: String,
    val name: String,
    val shortname: String,
) : Parcelable

@Parcelize
data class MatchItem(
    val date: String,
    val venue: String,
    val teams: List<String>,
    val matchType: String,
    val matchEnded: Boolean,
    val teamInfo: List<TeamInfoItem>,
    val seriesId: String,
    val score: List<ScoreItem>,
    val bbbEnabled: Boolean,
    val name: String,
    val dateTimeGMT: String,
    val fantasyEnabled: Boolean,
    val hasSquad: Boolean,
    val id: String,
    val matchStarted: Boolean,
    val status: String,
) : Parcelable
