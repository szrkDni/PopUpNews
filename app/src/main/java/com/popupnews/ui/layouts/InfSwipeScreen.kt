package com.popupnews.ui.layouts


import android.util.Log
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.popupnews.R
import com.popupnews.data.local.NewsViewModel
import com.popupnews.data.model.Article
import com.popupnews.data.model.ReadableArticle
import com.popupnews.ui.theme.MyTypography
import com.popupnews.utils.LocalNewsViewModel
import com.popupnews.utils.formatDescription
import com.popupnews.utils.formatTitle
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

const val boxWidth = 270
const val boxHeight = 360

@Composable
fun InfSwipeScreen(category: String, onArticleClick : (ReadableArticle) -> Unit, onBackClick: () -> Unit) {

    val newsViewModel :NewsViewModel = viewModel()

    LaunchedEffect(Unit) {
        newsViewModel.loadArticles(category = category)
    }

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(0.dp, 10.dp, 0.dp, 0.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        /*Icon(
            painter = painterResource(R.drawable.backarrow),
            contentDescription = "Navigate back",
            tint = Color.White, //modify to be compatible with different themes
            modifier = Modifier
                .size(30.dp)
                .clickable { onBackClick.invoke() }
        )*/

        PageTitle(title = category.replaceFirstChar { it-32 }, fontSize = 35.sp)
    }




    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 50.dp, 0.dp, 0.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        CompositionLocalProvider(LocalNewsViewModel provides newsViewModel) {
            when {
                newsViewModel.articles.isEmpty() -> {
                    CenterBox(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {


                    SwipeableCardStack(onClick = onArticleClick)

                    ArticleItemCard(
                        modifier = Modifier
                            .size(350.dp, 150.dp)
                            .fillMaxSize()
                    ) {
                        ArticleItemCardContent(newsViewModel)
                    }


                }
            }
        }



    }

}

@Composable
fun ArticleItemCardContent(newsViewModel: NewsViewModel) {
    // Get the current description (or an empty string if null)
    val description = newsViewModel.articles.lastOrNull()?.formatDescription() ?: ""

    // Crossfade animates between different description states
    Crossfade(
        targetState = description,
        animationSpec = tween(durationMillis = 300)
    ) { currentDescription ->
        // Wrap the Text in a Box that centers its content
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = currentDescription,
                modifier = Modifier
                    .padding(5.dp)
                    .verticalScroll(rememberScrollState()),
                style = MyTypography.labelMedium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun SwipeableCardStack(onClick: (ReadableArticle) -> Unit){

    val viewModel = LocalNewsViewModel.current

    CenterBox(
        modifier = Modifier.size(boxWidth.dp, boxHeight.dp)
    ) {

        if (viewModel.articles.isEmpty()) {
            Text(text = "No more articles in this topic", style = MyTypography.titleLarge, modifier = Modifier.padding(5.dp, 5.dp))
        }
        else{

            DisplayStackedSwipeableCards(viewModel,onClick)
        }
    }

}

@Composable
fun DisplayStackedSwipeableCards(
    viewModel: NewsViewModel,
    onClick: (ReadableArticle) -> Unit
)
{
    viewModel.articles.forEachIndexed { index, cardItem ->
        if (index == viewModel.articles.size-1) {
            SwipeableCard(
                cardItem = cardItem,
                onClick = onClick
            )
        }
        else {

            val depth = (viewModel.articles.size - index - 1).coerceAtMost(4)

            ArticleItemCard(
                //modifier = if (index.between(viewModel.articles.size-4, viewModel.articles.size)) SpecifyModifier((viewModel.articles.size - index)-1) else SpecifyModifier(3)

                modifier = AnimatedSpecifyModifier(depth-1)
            ) {
                when(val item = viewModel.articles.getOrNull(viewModel.articles.size-2)){
                    null ->Text(text = "No more articles", style = MyTypography.titleLarge, modifier = Modifier.padding(5.dp, 5.dp))
                    else ->Text(text = item.formatTitle(), style = MyTypography.titleLarge, modifier = Modifier.padding(5.dp, 5.dp))

                }
            }
        }
    }
}

@Composable
fun AnimatedSpecifyModifier(depth: Int): Modifier {
    // Számoljuk ki a cél értékeket a mélység alapján.
    val targetRotation = (depth * 3).toFloat()
    val targetOffsetX = (depth * boxWidth / 28).dp
    val targetOffsetY = (depth * boxHeight / 57).dp

    // Animateáljuk a forgatás értékét.
    val animatedRotation by animateFloatAsState(
        targetValue = targetRotation,
        animationSpec = tween(durationMillis = 300)
    )

    // Animateáljuk az eltolás értékeit.
    val animatedOffsetX by animateDpAsState(
        targetValue = targetOffsetX,
        animationSpec = tween(durationMillis = 300)
    )
    val animatedOffsetY by animateDpAsState(
        targetValue = targetOffsetY,
        animationSpec = tween(durationMillis = 300)
    )

    return Modifier
        .size(boxWidth.dp, boxHeight.dp)
        .rotate(animatedRotation)
        .offset(x = animatedOffsetX, y = animatedOffsetY)
}

@Composable
fun SwipeableCard(
    cardItem: Article,
    onClick: (ReadableArticle) -> Unit
) {
    val readable = ReadableArticle(cardItem.author,cardItem.content,cardItem.publishedAt,cardItem.title)
    Log.i("readable", "SwipeableCard: readable létrehozva ${readable}")

    SwipeLogic(
        swipeThreshold = 200f,
        animationDurationMillis = 300
    ){
        OutlinedCard(
            border = BorderStroke(4.dp, Color.Black),
            modifier = Modifier.clickable { onClick.invoke(readable) }
        ) {
            Log.i("readable", "SwipeableCard: readable átadva")
            CenterBox(
                modifier = Modifier.fillMaxSize()
            ){
                Text(text = cardItem.formatTitle(),
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
                            when {
                                offsetX.value > swipeThreshold -> {
                                    // Jobbra húzás
                                    offsetX.animateTo(
                                        targetValue = 1000f,
                                        animationSpec = tween(durationMillis = animationDurationMillis)
                                    )
                                    viewModel.articles.removeLastOrNull()
                                    offsetX.snapTo(0f)
                                }

                                offsetX.value < -swipeThreshold -> {
                                    // Balra húzás
                                    offsetX.animateTo(
                                        targetValue = -1000f,
                                        animationSpec = tween(durationMillis = animationDurationMillis)
                                    )
                                    viewModel.articles.removeLastOrNull()
                                    offsetX.snapTo(0f)
                                }

                                else -> {
                                    // Ha nem érte el a küszöböt, visszatér alaphelyzetbe
                                    offsetX.animateTo(
                                        0f,
                                        animationSpec = tween(durationMillis = animationDurationMillis)
                                    )
                                }
                            }
                        }
                    }
                )
            }
            .size(boxWidth.dp, boxHeight.dp)
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

