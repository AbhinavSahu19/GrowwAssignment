package com.example.stocksapp.presentation.explore

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stocksapp.R

@Composable
fun SearchBar(onSearchBarClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(MaterialTheme.colorScheme.surface)
            .padding(0.dp, 10.dp)
            .border(2.dp, MaterialTheme.colorScheme.primary, RoundedCornerShape(15.dp))
            .clickable { onSearchBarClicked() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(id = R.string.search_here),
            modifier = Modifier.padding(15.dp, 10.dp),
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
        )
        Icon(
            Icons.Default.Search,
            contentDescription = "Search Icon",
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier
                .padding(15.dp, 10.dp)
                .size(30.dp)
        )
    }
}

@Preview
@Composable
fun SearchBarPre() {
    SearchBar({})
}