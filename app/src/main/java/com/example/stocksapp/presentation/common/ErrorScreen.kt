package com.example.stocksapp.presentation.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocksapp.R

@Composable
fun ErrorScreen(
    msg: String,
    modifier: Modifier,
    isReloadEnabled: Boolean = false,
    onReload: () -> Unit = {},
) {
    val noData = stringResource(id = R.string.no_data)
    val noNetwork = stringResource(id = R.string.no_network)

    val text = when {
        msg == noData -> "No Data"
        msg.contains("Unable to resolve host", ignoreCase = true) -> "No network, Kindly turn on data."
        else -> msg
    }

    Column(
        modifier = modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = text,
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
        if (isReloadEnabled) {
            Button(onClick = { onReload() },
                modifier = Modifier.padding(20.dp, ),
                shape = RoundedCornerShape(10.dp)
            ) {
                Text(
                    text = "Reload",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(10.dp, 5.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun ErrorScreenPre() {
    ErrorScreen(" Text", Modifier, true)
}