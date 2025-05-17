package com.example.stocksapp.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stocksapp.presentation.details.DetailsScreen
import com.example.stocksapp.presentation.explore.ExploreScreen
import com.example.stocksapp.presentation.search.SearchScreen
import com.example.stocksapp.presentation.viewall.ViewAllScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainApp(
    navController: NavHostController = rememberNavController()
) {
    AnimatedNavHost(
        navController,
        startDestination = Screen.Explore.route,
    ) {
        composable(Screen.Explore.route) {
            ExploreScreen(
                navigateToStockDetails = { symbol ->
                    navController.navigate(Screen.StockDetail.createRoute(symbol))
                },
                navigateToSearch = {
                    navController.navigate(Screen.Search.route)
                },
                navigateToViewAll = { type ->
                    navController.navigate(Screen.ViewAll.createRoute(type))
                },
            )
        }
        composable(
            Screen.Search.route,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(durationMillis = 700, easing = FastOutSlowInEasing)
                ) + fadeIn(animationSpec = tween(700))
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(durationMillis = 600, easing = FastOutSlowInEasing)
                ) + fadeOut(animationSpec = tween(600))
            }
        ) {
            SearchScreen(
                navigateToStockDetails = { symbol ->
                    navController.navigate(Screen.StockDetail.createRoute(symbol))
                },
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.StockDetail.route,
            arguments = listOf(navArgument("symbol") { type = NavType.StringType })
        ) { backStackEntry ->
            DetailsScreen(
                navigateBack = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.ViewAll.route,
            arguments = listOf(navArgument("type") { type = NavType.StringType })
        ) { backStackEntry ->
            ViewAllScreen(
                navigateToStockDetails = { symbol ->
                    navController.navigate(Screen.StockDetail.createRoute(symbol))
                },
                navigateBack = { navController.popBackStack() }
            )
        }
    }
}
