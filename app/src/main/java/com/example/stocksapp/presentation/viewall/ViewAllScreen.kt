package com.example.stocksapp.presentation.viewall

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stocksapp.R
import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity
import com.example.stocksapp.presentation.common.ErrorScreen
import com.example.stocksapp.presentation.common.LoadingAnimation
import com.example.stocksapp.presentation.common.StockDisplayItem
import com.example.stocksapp.presentation.search.SearchKeyword
import com.example.stocksapp.utils.ResponseModel

@Composable
fun ViewAllScreen(
    viewModel: ViewAllViewModel = hiltViewModel(),
    navigateToStockDetails: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val type = viewModel.type
    val topGainers by viewModel.topGainers.collectAsState()
    val topLosers by viewModel.topLosers.collectAsState()
    val activelyTraded by viewModel.activelyTraded.collectAsState()
    val recentSearches by viewModel.recentSearches.collectAsState()

    BackHandler {
        navigateBack()
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
                .padding(paddingValues)
                .padding(15.dp, 10.dp)
        ) {
            when (type) {
                ViewAllType.TOP_GAINER -> {
                    Heading(text = stringResource(id = R.string.top_gainers))
                    HorizontalDivider()
                    when (topGainers) {
                        is ResponseModel.Error -> {
                            ErrorScreen(
                                msg = (topGainers as ResponseModel.Error).errorMsg,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(MaterialTheme.colorScheme.surface)
                            )
                        }

                        ResponseModel.Loading -> {
                            LoadingAnimation(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }

                        is ResponseModel.Success -> {
                            val data =
                                (topGainers as ResponseModel.Success<List<TopGainerEntity>>).data
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surface),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                items(data.size) { idx ->
                                    val item = data[idx]
                                    StockDisplayItem(
                                        ticker = item.ticker,
                                        price = item.price,
                                        changePercentage = item.changePercentage,
                                        changeAmount = item.changeAmount,
                                        { navigateToStockDetails(item.ticker) }
                                    )
                                }
                            }
                        }

                        null -> {
                            ErrorScreen(
                                msg = "Error",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(MaterialTheme.colorScheme.surface)
                            )
                        }
                    }

                }

                ViewAllType.TOP_LOSER -> {
                    Heading(text = stringResource(id = R.string.top_losers))
                    HorizontalDivider()
                    when (topLosers) {
                        is ResponseModel.Error -> {
                            ErrorScreen(
                                msg = (topLosers as ResponseModel.Error).errorMsg,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }

                        ResponseModel.Loading -> {
                            LoadingAnimation(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }

                        is ResponseModel.Success -> {
                            val data =
                                (topLosers as ResponseModel.Success<List<TopLooserEntity>>).data
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surface),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                items(data.size) { idx ->
                                    val item = data[idx]
                                    StockDisplayItem(
                                        ticker = item.ticker,
                                        price = item.price,
                                        changePercentage = item.changePercentage,
                                        changeAmount = item.changeAmount,
                                        { navigateToStockDetails(item.ticker) }
                                    )
                                }
                            }
                        }

                        null -> {
                            ErrorScreen(
                                msg = "Error",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(MaterialTheme.colorScheme.surface)
                            )
                        }
                    }
                }

                ViewAllType.ACTIVELY_TRADED -> {
                    Heading(text = stringResource(id = R.string.actively_traded))
                    HorizontalDivider()

                    when (activelyTraded) {
                        is ResponseModel.Error -> {
                            ErrorScreen(
                                msg = (activelyTraded as ResponseModel.Error).errorMsg,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }

                        ResponseModel.Loading -> {
                            LoadingAnimation(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            )
                        }

                        is ResponseModel.Success -> {
                            val data =
                                (activelyTraded as ResponseModel.Success<List<ActivelyTradedEntity>>).data
                            LazyVerticalGrid(
                                columns = GridCells.Fixed(2),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.surface),
                                horizontalArrangement = Arrangement.SpaceEvenly,
                            ) {
                                items(data.size) { idx ->
                                    val item = data[idx]
                                    StockDisplayItem(
                                        ticker = item.ticker,
                                        price = item.price,
                                        changePercentage = item.changePercentage,
                                        changeAmount = item.changeAmount,
                                        { navigateToStockDetails(item.ticker) }
                                    )
                                }
                            }
                        }

                        null -> {
                            ErrorScreen(
                                msg = "Error",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                                    .background(MaterialTheme.colorScheme.surface)
                            )
                        }
                    }
                }

                ViewAllType.RECENT_SEARCHES -> {
                    Heading(text = stringResource(id = R.string.recent_search))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surface),
                    ) {
                        recentSearches?.let {
                            items(recentSearches!!.size) { idx ->
                                val item = recentSearches!![idx]
                                SearchKeyword(
                                    symbol = item.symbol,
                                    name = item.name,
                                    region = item.region,
                                    { navigateToStockDetails(item.symbol) })
                            }
                        }
                    }

                }
            }
        }
    }
}


@Composable
fun Heading(text: String) {
    Text(
        text = text,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
        color = MaterialTheme.colorScheme.onPrimaryContainer,
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(15.dp, 10.dp)
    )
}