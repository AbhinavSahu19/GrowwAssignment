package com.example.stocksapp.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stocksapp.presentation.common.ErrorScreen
import com.example.stocksapp.presentation.common.LoadingAnimation
import com.example.stocksapp.utils.ResponseModel

@Composable
fun ExploreScreen(
    viewModel: ExploreViewModel = hiltViewModel(),
    navigateToStockDetails: (String) -> Unit,
    navigateToSearch: () -> Unit,
    navigateToViewAll: (String) -> Unit
) {
    val stocksData by viewModel.stocksData.collectAsState()
    val recentSearches by viewModel.recentKeywords.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) { paddingValues ->
        when (stocksData) {
            is ResponseModel.Error -> {
                ErrorScreen(
                    msg = (stocksData as ResponseModel.Error).errorMsg,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(paddingValues)
                )
            }

            ResponseModel.Loading -> {
                LoadingAnimation(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            is ResponseModel.Success -> {
                ExploreScreenBody(
                    recentSearches,
                    (stocksData as ResponseModel.Success<ExploreScreenData>).data,
                    navigateToSearch,
                    navigateToStockDetails,
                    navigateToViewAll,
                    Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}

