package com.sapient.cricketsample.ui.match

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sapient.cricketsample.data.model.MatchItem
import com.sapient.cricketsample.data.model.NetworkCallResult
import com.sapient.cricketsample.data.model.ScoreItem
import com.sapient.cricketsample.data.model.TeamInfoItem
import com.sapient.cricketsample.ui.main.LocalNavController

@Composable
fun MatchListScreen(
    modifier: Modifier = Modifier,
    matchViewModel: CricketMatchViewModel = hiltViewModel(),
) {

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(coroutineScope) {
        matchViewModel.fetchMatches()
    }

    val matches = matchViewModel.matchLiveData.observeAsState()

    when (val result = matches.value) {
        is NetworkCallResult.Success -> {
            LazyColumn(modifier = modifier) {
                items(result.value) {
                    MatchSummary(it)
                }
            }
        }

        is NetworkCallResult.Error -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(text = "Unknown error")
            }
        }

        else -> {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
    }


}

@Composable
fun MatchSummary(matchSummary: MatchItem, modifier: Modifier = Modifier) {
    val navController = LocalNavController.current
    Card(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        onClick = {
            navController.navigate(
                "matchDetail/${matchSummary.id}"
            )
        }
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Match Summary",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Teams: ${matchSummary.teams.joinToString(" vs ")}")
            Text(text = "Status: ${matchSummary.status}")
            Text(text = "Venue: ${matchSummary.venue}")
            Text(text = "Date: ${matchSummary.date}")
        }
    }
}

@Preview
@Composable
fun PreviewMatchSummaryUI() {
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
    MatchSummary(sampleMatch)
}