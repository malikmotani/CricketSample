package com.sapient.cricketsample.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sapient.cricketsample.ui.match.TopBarSetup
import com.sapient.cricketsample.ui.navigation.AppNavigation
import com.sapient.cricketsample.ui.theme.CricketSampleTheme
import dagger.hilt.android.AndroidEntryPoint

val LocalNavController = compositionLocalOf<NavController> {
    error("No NavController found!")
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CricketSampleTheme {
                val navController = rememberNavController()
                CompositionLocalProvider(LocalNavController provides navController) {
                    Scaffold(
                        modifier = Modifier.fillMaxSize(),
                        topBar = {
                            TopBarSetup()
                        },
                    ) { innerPadding ->
                        AppNavigation(
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }
        }
    }
}
