package com.popupnews.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.popupnews.ui.theme.MyTypography

@Composable
fun ReadArticleScreen(modifier: Modifier = Modifier, onBackClick : () -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Read Article Screen",
            modifier = modifier
                .fillMaxWidth()
                .clickable { onBackClick.invoke() },
            style = MyTypography.titleLarge)
    }
}