package com.popupnews.ui.layouts

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.popupnews.topics
import com.popupnews.ui.theme.MyTypography
import com.popupnews.utils.TopicItem

@Composable
fun TopicScreen(onClick : (String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        PageTitle(title = "Choose Topic", fontSize = 35.sp)

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(0.dp,20.dp,0.dp,0.dp)
        )
        {
            items(topics.size){ i ->
                CreateTopicCards(topicItem = topics[i], onClick = onClick)
            }
        }
    }
}


@Composable
fun CreateTopicCards(topicItem : TopicItem, onClick : (String) -> Unit)
{
    OutlinedCard(
        border = BorderStroke(2.dp, Color.Black),
        modifier = Modifier
            .size(width = 100.dp, height = 200.dp)
            .clickable {
                onClick.invoke(topicItem.category)
                Log.i("clickLog", "Clicked on ${topicItem.category}")
            }
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    painter = painterResource(id = topicItem.imageRes),
                    contentDescription = topicItem.topic,
                    modifier = Modifier.size(50.dp)
                )
                Text(
                    text = topicItem.topic,
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Monospace,
                    modifier = Modifier.padding(0.dp,20.dp,0.dp,0.dp),
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }

    }
}



@Composable
fun PageTitle(
    title : String,
    fontSize: TextUnit,
    textStyle: TextStyle = MyTypography.titleLarge)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = title,
            fontSize = fontSize, //35.sp
            modifier = Modifier
                .fillMaxWidth(),
            style = textStyle
        )
    }
}
