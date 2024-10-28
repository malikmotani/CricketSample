package com.sapient.cricketsample.ui.match

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.ScoreItem
import com.sapient.cricketsample.data.model.TeamInfoItem

@Composable
fun MatchDetailScreen(
    modifier: Modifier = Modifier,
    matchId: String,
    matchViewModel: CricketMatchViewModel,
) {
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(coroutineScope) {
        matchViewModel.fetchMatchById(matchId)
    }

    val matchDetail = matchViewModel.matchDetailLiveData.observeAsState()
    matchDetail.value?.let { match ->
        MatchDetail(match, modifier)
    }
}


@Composable
fun MatchDetail(match: MatchItem, modifier: Modifier = Modifier) {

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()

    ) {
        Text(text = match.name, fontWeight = FontWeight.Bold)
        Text(text = "Match Type: ${match.matchType}")
        Text(text = "Status: ${match.status}")
        Text(text = "Venue: ${match.venue}")
        Text(text = "Date: ${match.date}")

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Teams:")
        match.teams.forEach { team ->
            Text(text = "- $team")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Scores:")
        match.score.forEach { score ->
            Column {
                Text(text = score.inning)
                Text(text = "Runs: ${score.r}, Wickets: ${score.w}, Overs: ${score.o}")
            }
        }
    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewMatchDetailsUI() {
    val sampleMatch = MatchItem(
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
    )

    MatchDetail(sampleMatch, Modifier)
}

