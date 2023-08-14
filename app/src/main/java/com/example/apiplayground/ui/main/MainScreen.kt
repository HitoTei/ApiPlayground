package com.example.apiplayground.ui.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.apiplayground.ui.apiDestinationList
import com.example.apiplayground.ui.theme.ApiPlaygroundTheme

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    ApiPlaygroundTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = "apiList",
            ) {
                composable("apiList") {
                    ApiList(
                        apiList = apiDestinationList.map { it.apiData },
                        onTileClick = { navController.navigate(it) }
                    )
                }
                for (apiDestination in apiDestinationList) {
                    composable(apiDestination.apiData.name) {
                        apiDestination.screen()
                    }
                }
            }
        }
    }
}
