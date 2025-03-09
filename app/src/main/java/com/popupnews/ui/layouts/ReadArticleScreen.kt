package com.popupnews.ui.layouts

import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberAsyncImagePainter
import com.popupnews.data.model.ReadableArticle
import com.popupnews.ui.theme.MyTypography

@Composable
fun ReadArticleScreen(readable : ReadableArticle, urlToImg: String,  onBackClick : () -> Unit) {
   Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        PageTitle(title = readable.title, fontSize = 25.sp)

        Image(
            painter = rememberAsyncImagePainter(urlToImg),
            contentDescription = "article image",
            modifier = Modifier.padding(0.dp,0.dp,0.dp,10.dp).clip(RoundedCornerShape(16.dp)))

        OutlinedCard(
            modifier = Modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            border = BorderStroke(4.dp, Color.Black)
        ) {
            Text(text = readable.content, modifier = Modifier.padding(5.dp), textAlign = TextAlign.Center)
        }




    }


}