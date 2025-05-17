package com.example.stocksapp.presentation.search

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.stocksapp.R
import com.example.stocksapp.api.responsedto.search.SearchResponseItem
import com.example.stocksapp.presentation.common.ErrorScreen
import com.example.stocksapp.presentation.common.LoadingAnimation
import com.example.stocksapp.utils.ResponseModel
import kotlinx.coroutines.delay

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    navigateToStockDetails: (String) -> Unit,
    navigateBack: () -> Unit
) {
    val apiKeywords by viewModel.apiKeywords.collectAsState()
    val dbKeywords by viewModel.dbKeywords.collectAsState()

    BackHandler {
        navigateBack()
    }
    val keyword = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

    LaunchedEffect(Unit) {
        delay(300)
        focusRequester.requestFocus()
        keyboardController?.show()
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
        ) {
            SearchBar(
                keyword.value,
                focusRequester,
                {
                    keyword.value = it
                    viewModel.filterDbKeywords(keyword.value.text)
                    viewModel.getApiKeywords(keyword.value.text)
                })
            LazyColumn {
                if (dbKeywords.isNotEmpty()) {
                    items(dbKeywords.size) { idx ->
                        val item = dbKeywords[idx]
                        SearchKeyword(
                            item.symbol,
                            item.name,
                            item.region,
                            {
                                navigateToStockDetails(item.symbol)
                            }
                        )
                    }
                    item {
                        Spacer(modifier = Modifier.height(2.dp))
                        HorizontalDivider(modifier = Modifier.padding(10.dp))
                        Spacer(modifier = Modifier.height(2.dp))
                    }
                }
                when (apiKeywords) {
                    is ResponseModel.Error -> {
                        item {
                            ErrorScreen(
                                msg = (apiKeywords as ResponseModel.Error).errorMsg,
                                modifier = Modifier
                                    .weight(1f)
                                    .background(MaterialTheme.colorScheme.surface)
                            )
                        }
                    }

                    ResponseModel.Loading -> {
                        if (keyword.value.text.isNotBlank()) {
                            item {
                                LoadingAnimation(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f)
                                )
                            }
                        }
                    }

                    is ResponseModel.Success -> {
                        val data =
                            (apiKeywords as ResponseModel.Success<List<SearchResponseItem>>).data
                        if (data.isEmpty() && dbKeywords.isEmpty()) {
                            item {
                                ErrorScreen(
                                    msg = stringResource(id = R.string.no_response),
                                    modifier = Modifier
                                        .weight(1f)
                                        .background(MaterialTheme.colorScheme.surface)
                                )
                            }
                        } else {
                            items(data.size) { idx ->
                                val item = data[idx]
                                if (!dbKeywords.any { entity -> entity.symbol == item.symbol }) {
                                    SearchKeyword(
                                        item.symbol,
                                        item.name,
                                        item.region,
                                        {
                                            viewModel.addKeywordToDb(item)
                                            navigateToStockDetails(item.symbol)
                                        }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    keyword: TextFieldValue,
    focusRequester: FocusRequester,
    onChange: (TextFieldValue) -> Unit
) {
    TextField(
        value = keyword,
        onValueChange = {
            onChange(it)
        },
        textStyle = TextStyle.Default.copy(fontSize = 18.sp),
        modifier = Modifier
            .focusable()
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .height(80.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(horizontal = 15.dp, vertical = 13.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(20.dp)),
        placeholder = {
            Text(
                text = "Search Here...",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        trailingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(15.dp, 10.dp)
                    .size(30.dp)
            )
        },
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        singleLine = true,
    )
}

@Composable
fun SearchKeyword(
    symbol: String,
    name: String,
    region: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp, 3.dp)
            .clickable { onClick() },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(15.dp, 5.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = symbol,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(0.dp, 2.dp),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = region,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Text(
                text = name,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(0.dp, 2.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview
@Composable
fun SearchBarPre(){
    SearchBar(
        TextFieldValue(),
        FocusRequester.Default,
        {}
    )
}
@Preview
@Composable
fun SearchKeywordPre() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        SearchKeyword(
            "S0A.FRK", "PT Steel Pipe Industry of Indonesia Tbk", "United States", {}
        )
        SearchKeyword(
            "S0A.FRK", "PT Steel Pipe Industry of Indonesia Tbk", "United States", {}
        )
        SearchKeyword(
            "S0A.FRK", "PT Steel Pipe Industry of Indonesia Tbk", "United States", {}
        )
        SearchKeyword(
            "S0A.FRK", "PT Steel Pipe Industry of Indonesia Tbk", "United States", {}
        )
        SearchKeyword(
            "S0A.FRK", "PT Steel Pipe Industry of Indonesia Tbk", "United States", {}
        )
        SearchKeyword(
            "S0A.FRK", "PT Steel Pipe Industry of Indonesia Tbk", "United States", {}
        )
    }
}