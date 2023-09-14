package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {

                        Main()
                    }


                }
            }
        }
    }
}


@Composable
fun Circle(size: Dp = 16.dp) {

    Box(
        modifier = Modifier
            .size(size)
            .clip(RoundedCornerShape(50))
            .background(Color.Gray)
    ) {

    }

}

@Preview
@Composable
fun CirclePrev() {
    MyApplicationTheme {
        Circle()
    }
}


@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun Main() {

    val height = 70.dp
    val width = 140.dp
    val circlePadding = 4.dp

    val day = 0
    val night = 1

    val swipeableState = rememberSwipeableState(0)
    val sizePx = with(LocalDensity.current) { (width - height).toPx() }
    val anchors = mapOf(0f to day, sizePx to night) // Maps anchor points (in px) to states

    val scope = rememberCoroutineScope()



    Row(
        modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(height))
            .border(1.dp, Color.DarkGray, CircleShape)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal
            )
            .background(Color.Transparent)
            .then(
                if (swipeableState.currentValue == day) Modifier.paint(
                    painterResource(id = R.drawable.switch_body_day),
                    contentScale = ContentScale.FillBounds
                ) else Modifier.paint(
                    painterResource(id = R.drawable.switch_body_night),
                    contentScale = ContentScale.FillBounds
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(height)
                .padding(circlePadding)
                .clip(RoundedCornerShape(50))
                .then(
                    if (swipeableState.currentValue == day) Modifier.paint(
                        painterResource(id = R.drawable.switch_btn_sun),
                        contentScale = ContentScale.FillBounds
                    ) else Modifier.paint(
                        painterResource(id = R.drawable.switch_btn_moon),
                        contentScale = ContentScale.FillBounds
                    )
                )
                .clickable {
                    scope.launch {

                        if (swipeableState.currentValue == day) {
                            swipeableState.animateTo(night)
                        } else {
                            swipeableState.animateTo(day)
                        }

                    }


                }
        )
    }

}

@Preview
@Composable
fun MainPrev() {
    MyApplicationTheme {
        Main()
    }
}
