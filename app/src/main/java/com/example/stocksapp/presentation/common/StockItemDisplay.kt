package com.example.stocksapp.presentation.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocksapp.ui.theme.green
import com.example.stocksapp.ui.theme.red

@Composable
fun StockDisplayItem(
    ticker: String,
    price: String,
    changePercentage: String,
    changeAmount: String,
    onClick: () -> Unit
) {
    val changeAmountValue = changeAmount.toFloatOrNull() ?: 0f
    val changePercentageValue = changePercentage.trimEnd('%').toFloatOrNull() ?: 0f
    val isPositive = changeAmountValue >= 0
    val priceColor = if (isPositive) green else red

    val change = buildAnnotatedString {
        if (isPositive) append('+')
        append(String.format("%.2f", changeAmountValue))
        append(" (")
        if (isPositive) append('+')
        append(String.format("%.2f", changePercentageValue))
        append("%)")
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable { onClick() },
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .padding(12.dp)
        ) {
            Text(
                text = ticker,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                modifier = Modifier.padding(0.dp, 2.dp)
            )
            Text(
                text = price,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp,
                color = priceColor,
            )
            Text(
                text = change,
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(0.dp, 2.dp)
            )
        }
    }
}

@Preview
@Composable
fun StockDisplayItemPre() {
    StockDisplayItem("HUBCW", "0.0319", "-15.711%", "-90.226", {})
}