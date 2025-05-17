package com.example.stocksapp.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.stocksapp.R
import com.example.stocksapp.db.entity.ActivelyTradedEntity
import com.example.stocksapp.db.entity.SearchKeywordEntity
import com.example.stocksapp.db.entity.TopGainerEntity
import com.example.stocksapp.db.entity.TopLooserEntity
import com.example.stocksapp.presentation.common.HeadingDisplay
import com.example.stocksapp.presentation.common.StockDisplayItem
import com.example.stocksapp.presentation.search.SearchKeyword
import com.example.stocksapp.presentation.viewall.ViewAllType

@Composable
fun ExploreScreenBody(
    recentSearch: List<SearchKeywordEntity>,
    stocksData: ExploreScreenData,
    onSearchBarClicked: () -> Unit,
    navigateToStockDetails: (String) -> Unit,
    onViewAll: (String) -> Unit,
    modifier: Modifier
) {
    LazyColumn(
        contentPadding = PaddingValues(15.dp, 5.dp),
        modifier = modifier.background(MaterialTheme.colorScheme.surface)
    ) {
        item {
            SearchBar(onSearchBarClicked)
        }

        if (recentSearch.isNotEmpty()) {
            item {
                HeadingDisplay(
                    text = stringResource(id = R.string.recent_search),
                    onViewAll = { onViewAll(ViewAllType.RECENT_SEARCHES.name) }
                )
            }

            items(Math.min(recentSearch.size, 5)) { idx ->
                val keyword = recentSearch[idx]
                SearchKeyword(
                    keyword.symbol,
                    keyword.name,
                    keyword.region,
                    { navigateToStockDetails(keyword.symbol) }
                )
            }

            item {
                Spacer(modifier = Modifier.height(5.dp))
                HorizontalDivider(modifier = Modifier.padding(10.dp))
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

        item {
            HeadingDisplay(
                text = stringResource(id = R.string.top_gainers),
                onViewAll = { onViewAll(ViewAllType.TOP_GAINER.name) }
            )
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalArrangement = Arrangement.SpaceEvenly,
                userScrollEnabled = false
            ) {
                items(stocksData.topGainers.size) { idx ->
                    val item = stocksData.topGainers[idx]
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
        item {
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider(modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            HeadingDisplay(
                text = stringResource(id = R.string.top_losers),
                onViewAll = { onViewAll(ViewAllType.TOP_LOSER.name) }
            )
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalArrangement = Arrangement.SpaceEvenly,
                userScrollEnabled = false,
            ) {
                items(stocksData.topLosers.size) { idx ->
                    val item = stocksData.topLosers[idx]
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
        item {
            Spacer(modifier = Modifier.height(5.dp))
            HorizontalDivider(modifier = Modifier.padding(10.dp))
            Spacer(modifier = Modifier.height(5.dp))
        }
        item {
            HeadingDisplay(
                text = stringResource(id = R.string.actively_traded),
                onViewAll = { onViewAll(ViewAllType.ACTIVELY_TRADED.name) }
            )
        }
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 400.dp)
                    .background(MaterialTheme.colorScheme.surface),
                horizontalArrangement = Arrangement.SpaceEvenly,
                userScrollEnabled = false
            ) {
                items(stocksData.activeTraded.size) { idx ->
                    val item = stocksData.activeTraded[idx]
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
    }
}

@Preview
@Composable
fun ExploreScreenBodyPre() {
    ExploreScreenBody(
        listOf(
            SearchKeywordEntity(
                "IMB",
                "Science Applications International Corp",
                "Equity",
                "USA",
                "09:30",
                "16:00",
                "UTC-04",
                "USD",
                "1.0000"
            ),
            SearchKeywordEntity(
                "IMB",
                "Science Applications International Corp",
                "Equity",
                "USA",
                "09:30",
                "16:00",
                "UTC-04",
                "USD",
                "1.0000"
            ),
            SearchKeywordEntity(
                "IMB",
                "Science Applications International Corp",
                "Equity",
                "USA",
                "09:30",
                "16:00",
                "UTC-04",
                "USD",
                "1.0000"
            ),
            SearchKeywordEntity(
                "IMB",
                "Science Applications International Corp",
                "Equity",
                "USA",
                "09:30",
                "16:00",
                "UTC-04",
                "USD",
                "1.0000"
            ),
            SearchKeywordEntity(
                "IMB",
                "Science Applications International Corp",
                "Equity",
                "USA",
                "09:30",
                "16:00",
                "UTC-04",
                "USD",
                "1.0000"
            ),
            SearchKeywordEntity(
                "IMB",
                "Science Applications International Corp",
                "Equity",
                "USA",
                "09:30",
                "16:00",
                "UTC-04",
                "USD",
                "1.0000"
            ),
            SearchKeywordEntity(
                "IMB",
                "Science Applications International Corp",
                "Equity",
                "USA",
                "09:30",
                "16:00",
                "UTC-04",
                "USD",
                "1.0000"
            ),
        ),
        ExploreScreenData(
            listOf(
                TopGainerEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopGainerEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopGainerEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopGainerEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopGainerEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopGainerEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopGainerEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
            ),
            listOf(
                TopLooserEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopLooserEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopLooserEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopLooserEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopLooserEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopLooserEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                TopLooserEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
            ),
            listOf(
                ActivelyTradedEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                ActivelyTradedEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                ActivelyTradedEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                ActivelyTradedEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                ActivelyTradedEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                ActivelyTradedEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
                ActivelyTradedEntity("HUBCW", "0.0319", "0.0142", "80.226", "77661"),
            ),
        ),
        {},
        {},
        {},
        Modifier
    )
}