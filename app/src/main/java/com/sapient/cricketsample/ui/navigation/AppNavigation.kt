package com.sapient.cricketsample.ui.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sapient.cricketsample.ui.main.LocalNavController
import com.sapient.cricketsample.ui.match.CricketMatchViewModel
import com.sapient.cricketsample.ui.match.MatchDetailScreen
import com.sapient.cricketsample.ui.match.MatchListScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {

    val navController = LocalNavController.current

    NavHost(
        navController = navController as NavHostController, startDestination = "home"
    ) {

        composable("home") {
            MatchListScreen(modifier = modifier)
        }

        composable("matchDetail/{matchId}") { backStackEntry ->
            val parentEntry = remember(backStackEntry) {
                navController.getBackStackEntry("home")
            }
            val parentViewModel = hiltViewModel<CricketMatchViewModel>(parentEntry)
            val matchId: String? = backStackEntry.arguments?.getString("matchId")
            matchId?.let {
                MatchDetailScreen(
                    modifier = modifier,
                    matchId = it,
                    matchViewModel = parentViewModel
                )
            }
        }

    }

}
