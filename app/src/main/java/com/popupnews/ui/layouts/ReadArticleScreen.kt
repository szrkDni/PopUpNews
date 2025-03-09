package com.popupnews.ui.layouts

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.popupnews.data.model.ReadableArticle
import com.popupnews.ui.theme.MyTypography

@Composable
fun ReadArticleScreen(readable : ReadableArticle,  onBackClick : () -> Unit) {
   Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
       Log.i("readable", "ReadArticleScreen: readable fogadva ${readable}")
        PageTitle(title = "Read Article Screen", fontSize = 35.sp)

        OutlinedCard(
            modifier = Modifier
                .verticalScroll(rememberScrollState()),
            border = BorderStroke(4.dp, Color.Black)
        ) {
            Text(text = readable.title)
            Text(text = readable.content)
        }
    }


}