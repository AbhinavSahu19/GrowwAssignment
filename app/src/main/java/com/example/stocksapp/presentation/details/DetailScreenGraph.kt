package com.example.stocksapp.presentation.details

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocksapp.presentation.common.LoadingAnimation
import io.github.yashctn88.candlechartcompose.model.CandlestickData
import io.github.yashctn88.candlechartcompose.ui.CandlestickChart
import kotlin.math.max

@Composable
fun DetailScreenGraph(
    duration: GraphDurationEnum,
    graphData: List<CandlestickData>,
    onChange: (GraphDurationEnum) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(380.dp)
                .padding(
                    start = 15.dp,
                    end = 0.dp,
                    top = 20.dp,
                    bottom = 0.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            if(graphData.isNotEmpty()){
                CandlestickChart(
                    data = graphData,
                    minY = graphData.minOf { it.low },
                    maxY = graphData.maxOf { it.high },
                    candleWidth = max(4f, 300f / graphData.size)
                )
            }
            else {
                LoadingAnimation(modifier = Modifier)
            }
        }
        HorizontalDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            GraphDurationButton(
                duration == GraphDurationEnum.DAYS_7,
                text = GraphDurationEnum.DAYS_7.text,
                { onChange(GraphDurationEnum.DAYS_7) })
            GraphDurationButton(
                duration == GraphDurationEnum.DAYS_15,
                text = GraphDurationEnum.DAYS_15.text,
                { onChange(GraphDurationEnum.DAYS_15) })
            GraphDurationButton(
                duration == GraphDurationEnum.MONTHS_1,
                text = GraphDurationEnum.MONTHS_1.text,
                { onChange(GraphDurationEnum.MONTHS_1) })
            GraphDurationButton(
                duration == GraphDurationEnum.MONTHS_3,
                text = GraphDurationEnum.MONTHS_3.text,
                { onChange(GraphDurationEnum.MONTHS_3) })
            GraphDurationButton(
                duration == GraphDurationEnum.MONTHS_6,
                text = GraphDurationEnum.MONTHS_6.text,
                { onChange(GraphDurationEnum.MONTHS_6) })
        }
    }
}

@Composable
fun GraphDurationButton(
    isSelected: Boolean,
    text: String,
    onClick: () -> Unit
) {
    val buttonColor =
        if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.surface

    val textColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimary
        else MaterialTheme.colorScheme.onSurface

    val outlineColor =
        if (isSelected) MaterialTheme.colorScheme.primary
        else MaterialTheme.colorScheme.outline

    OutlinedButton(
        onClick = { onClick() },
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 4.dp
        ),
        modifier = Modifier
            .defaultMinSize(minWidth = 1.dp, minHeight = 1.dp)
            .padding(horizontal = 4.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = buttonColor,
            contentColor = textColor,
        ),
        border = BorderStroke(1.dp, outlineColor)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(0.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Normal
        )
    }
}


@Preview
@Composable
fun Pre() {
    val graphData = listOf(
        CandlestickData(
            x = "2025 - 05 - 12",
            open = 252.5F,
            high = 253.81F,
            low = 244.65F,
            close = 253.69F
        ),
        CandlestickData(
            x = " 2025-05-13",
            open = 254.43F,
            high = 259.58F,
            low = 252.88F,
            close = 258.59F
        ),
        CandlestickData(
            x = " 2025-05-14",
            open = 257.6F,
            high = 260.5F,
            low = 256.22F,
            close = 257.82F
        ),
        CandlestickData(
            x = "2025-05-15",
            open = 259.01F,
            high = 267.43F,
            low = 258.61F,
            close = 266.68F
        ),
        CandlestickData(
            x = "2025-05-16",
            open = 266.35F,
            high = 267.98F,
            low = 264.59F,
            close = 266.76F
        )
    )
    DetailScreenGraph(duration = GraphDurationEnum.DAYS_7, graphData = graphData) {

    }
}