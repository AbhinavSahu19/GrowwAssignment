package com.example.stocksapp.presentation.details

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stocksapp.R
import com.example.stocksapp.presentation.common.ErrorScreen
import com.example.stocksapp.presentation.common.LoadingAnimation
import com.example.stocksapp.presentation.viewall.Heading
import com.example.stocksapp.utils.ResponseModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    viewModel: DetailsViewModel = hiltViewModel(),
    navigateBack: () -> Unit
) {
    val companyOverviewResponse by viewModel.companyOverview.collectAsState()
    val graphData by viewModel.graphList.collectAsState()
    val graphDuration by viewModel.graphDurationEnum.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    BackHandler {
        navigateBack()
    }
    val pullRefreshState =
        rememberPullRefreshState(isRefreshing, { viewModel.reloadCompanyOverview() })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) { paddingValues ->
        when (companyOverviewResponse) {
            is ResponseModel.Error -> {
                ErrorScreen(
                    msg = (companyOverviewResponse as ResponseModel.Error).errorMsg,
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(paddingValues),
                    true,
                    { viewModel.reloadCompanyOverview() }
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
                val overview =
                    (companyOverviewResponse as ResponseModel.Success<CompanyOverviewScreenData>).data
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(15.dp, 10.dp)
                        .padding(paddingValues)
                ) {
                    Heading(text = stringResource(id = R.string.stock_detail))
                    HorizontalDivider()
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .pullRefresh(pullRefreshState)
                            .background(MaterialTheme.colorScheme.surface),
                    ) {
                        PullRefreshIndicator(
                            refreshing = isRefreshing,
                            state = pullRefreshState,
                            modifier = Modifier
                                .zIndex(1f)
                                .align(Alignment.TopCenter)
                        )
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface),
                        ) {
                            item {
                                DetailHeading(
                                    overview.symbol,
                                    overview.name,
                                    overview.assetType,
                                    overview.exchange
                                )
                            }
                            item {
                                DetailScreenGraph(
                                    graphDuration,
                                    graphData,
                                    viewModel::filterGraph
                                )
                            }
                            item {
                                DetailsBody(
                                    overview.symbol,
                                    overview.description,
                                    overview.country,
                                    overview.currency,
                                    overview.sector,
                                    overview.industry,
                                    overview.marketCapitalization,
                                    overview.dividendYield,
                                    overview.profitMargin,
                                    overview.beta,
                                    overview.weekHigh52,
                                    overview.weekLow52,
                                    overview.dayMovingAverage50,
                                    overview.dayMovingAverage200,
                                    overview.sharesOutstanding,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun DetailsBodyPre() {
    DetailsBody(
        "IBM",
        "International Business Machines Corporation (IBM) is an American multinational technology company headquartered in Armonk, New York, with operations in over 170 countries. The company began in 1911, founded in Endicott, New York, as the Computing-Tabulating-Recording Company (CTR) and was renamed International Business Machines in 1924. IBM is incorporated in New York. IBM produces and sells computer hardware, middleware and software, and provides hosting and consulting services in areas ranging from mainframe computers to nanotechnology. IBM is also a major research organization, holding the record for most annual U.S. patents generated by a business (as of 2020) for 28 consecutive years. Inventions by IBM include the automated teller machine (ATM), the floppy disk, the hard disk drive, the magnetic stripe card, the relational database, the SQL programming language, the UPC barcode, and dynamic random-access memory (DRAM). The IBM mainframe, exemplified by the System/360, was the dominant computing platform during the 1960s and 1970s.",
        "USA",
        "USD",
        "TECHNOLOGY",
        "COMPUTER & OFFICE EQUIPMENT",
        "247851581000",
        "0.1111",
        "11.12",
        "0.629",
        "267.98",
        "158.72",
        "244.82",
        "227.92",
        "929397000"
    )
}

@Composable
fun DetailHeading(
    symbol: String,
    name: String,
    assetType: String,
    exchange: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(top = 10.dp, bottom = 5.dp)
    ) {
        Text(
            text = symbol,
            fontSize = 19.sp,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        Text(
            text = name,
            fontSize = 17.sp,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(10.dp, 2.dp)
        )
        Text(
            text = "${exchange}, ${assetType}",
            fontSize = 15.sp,
            fontWeight = FontWeight.Normal,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(10.dp, 0.dp)
        )
    }
}


@Composable
@Preview
fun DetailHeadingPre() {
    DetailHeading(
        "IBM",
        "International Business Machines",
        "Common Stock",
        "NYSE"
    )
}