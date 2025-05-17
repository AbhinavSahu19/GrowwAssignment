package com.example.stocksapp.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocksapp.R

@Composable
fun SearchBar(onSearchBarClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .height(70.dp)
            .background(MaterialTheme.colorScheme.surface),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            fontFamily = FontFamily.Serif,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier
                .padding(10.dp, 5.dp)
                .weight(1f)
                .background(MaterialTheme.colorScheme.surface)
        )
        Row(
            modifier = Modifier
                .height(70.dp)
                .background(MaterialTheme.colorScheme.surface)
                .padding(0.dp, 10.dp)
                .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(10.dp))
                .clickable { onSearchBarClicked() },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(id = R.string.search_here),
                modifier = Modifier.padding(15.dp, 0.dp),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )
            Icon(
                Icons.Default.Search,
                contentDescription = "Search Icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(15.dp, 0.dp)
                    .size(30.dp)
            )
        }
    }
    Spacer(modifier = Modifier.height(20.dp))
}

@Preview
@Composable
fun SearchBarPre() {
    SearchBar({})
}