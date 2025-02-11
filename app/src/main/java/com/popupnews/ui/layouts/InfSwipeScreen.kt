package com.popupnews.ui.layouts


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.popupnews.data.local.NewsViewModel
import com.popupnews.ui.theme.MyTypography
import com.popupnews.utils.LocalNewsViewModel
import com.popupnews.utils.formatDescription
import com.popupnews.utils.formatTitle
import kotlinx.coroutines.launch
import kotlin.math.roundToInt



@Composable
fun InfSwipeScreen(category: String, onArticleClick : () -> Unit, onBackClick: () -> Unit) {

    val newsViewModel :NewsViewModel = viewModel()

    LaunchedEffect(Unit) {
        newsViewModel.loadArticles(category = category)
    }

    Box(modifier = Modifier.size(20.dp).clickable { onBackClick.invoke() })
    PageTitle(title = category, fontSize = 35.sp)

    Column(
        modifier = Modifier.fillMaxSize().padding(0.dp,50.dp,0.dp,0.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        CompositionLocalProvider(LocalNewsViewModel provides newsViewModel) {
            when{
                newsViewModel.articles.isEmpty() -> {
                    CenterBox(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }
                else -> {
                    SwipeableCardStack(onClick = onArticleClick)
                }
            }
        }

       ArticleItemCard(
           modifier = Modifier.size(350.dp, 150.dp).fillMaxSize()
       ) {
           newsViewModel.articles.lastOrNull()?.let { Text(
               text = it.formatDescription(),
               modifier = Modifier
                   .fillMaxWidth()
                   .padding(5.dp)
                   .verticalScroll(rememberScrollState()),
               style = MyTypography.labelMedium
           ) }
       }
    }
}

@Composable
fun SwipeableCardStack(onClick: () -> Unit){

    val viewModel = LocalNewsViewModel.current

    CenterBox(
        modifier = Modifier.size(300.dp, 400.dp)
    ) {
        if (viewModel.articles.isEmpty()) {
            Text(text = "No more articles in this topic", style = MyTypography.titleLarge, modifier = Modifier.padding(5.dp, 5.dp))
        }
        else{
            viewModel.articles.forEachIndexed { index, cardText ->
                if (index == viewModel.articles.size-1) {
                    SwipeableCard(
                        cardText = cardText.formatTitle(),
                        onClick = onClick
                    )
                }
                else {
                    ArticleItemCard(
                        modifier = Modifier.size(300.dp, 400.dp)
                    ) {
                        when(val item = viewModel.articles.getOrNull(viewModel.articles.size-2)){
                            null ->Text(text = "No more articles", style = MyTypography.titleLarge, modifier = Modifier.padding(5.dp, 5.dp))
                            else ->Text(text = item.formatTitle(), style = MyTypography.titleLarge, modifier = Modifier.padding(5.dp, 5.dp))
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun SwipeableCard(
    cardText: String,
    onClick: () -> Unit) {

    SwipeLogic(
        swipeThreshold = 200f,
        animationDurationMillis = 300,
        onClick = onClick,
    ){
        OutlinedCard(
            border = BorderStroke(4.dp, Color.Black)
        ) {
            CenterBox(
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = cardText,
                    style = MyTypography.titleLarge,
                    modifier = Modifier.padding(5.dp, 5.dp))
            }
        }
    }
}

@Composable
fun SwipeLogic(
    swipeThreshold: Float,
    animationDurationMillis: Int,
    onClick: () -> Unit,
    content: @Composable () -> Unit
    )
{
    val scope = rememberCoroutineScope()

    val offsetX = remember { Animatable(0f) }

    val viewModel = LocalNewsViewModel.current

    Box(
        modifier = Modifier
            .offset { IntOffset(offsetX.value.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { change, dragAmount ->
                        change.consume()
                        scope.launch { offsetX.snapTo(offsetX.value + dragAmount.x) }
                    },
                    onDragEnd = {
                        scope.launch {
                            if (offsetX.value > swipeThreshold) {
                                offsetX.animateTo(
                                    targetValue = 1000f,
                                    animationSpec = tween(durationMillis = animationDurationMillis)
                                )

                                viewModel.articles.removeLastOrNull()

                                offsetX.snapTo(0f)
                            }
                            else {
                                offsetX.animateTo(0f, animationSpec = tween(durationMillis = animationDurationMillis))
                            }
                        }
                    }
                )
            }
            .clickable { onClick.invoke() }
            .size(width = 300.dp, height = 400.dp)
    )
    {content.invoke()}
}

@Composable
fun CenterBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        content.invoke()
    }
}

@Composable
fun ArticleItemCard(
    modifier: Modifier = Modifier,
    borderStroke: BorderStroke = BorderStroke(4.dp,Color.Black),
    content: @Composable () -> Unit){
    OutlinedCard(
        border = borderStroke,
        modifier = modifier
    ) {
        CenterBox(
            modifier = Modifier.fillMaxSize()
        ) {
            content.invoke()
        }
    }

}