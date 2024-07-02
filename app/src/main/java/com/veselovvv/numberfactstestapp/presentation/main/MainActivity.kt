package com.veselovvv.numberfactstestapp.presentation.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.veselovvv.numberfactstestapp.presentation.core.Routes
import com.veselovvv.numberfactstestapp.presentation.fact.FactDetailsScreen
import com.veselovvv.numberfactstestapp.presentation.fact.FactViewModel
import com.veselovvv.numberfactstestapp.presentation.facts.FactsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val factsViewModel = hiltViewModel<FactsViewModel>()
            val factViewModel = hiltViewModel<FactViewModel>()
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Routes.Main.getRoute()) {
                composable(Routes.Main.getRoute()) {
                    MainScreen(
                        factsViewModel = factsViewModel,
                        factViewModel = factViewModel,
                        navController = navController
                    )
                }
                composable(Routes.FactDetails.getRoute()) {
                    FactDetailsScreen(viewModel = factViewModel)
                }
            }
        }
    }
}