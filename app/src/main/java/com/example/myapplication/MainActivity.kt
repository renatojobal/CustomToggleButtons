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
import androidx.compose.material3.Divider

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
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
import kotlinx.coroutines.launch
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

                    Main()

                }
            }
        }
    }
}

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun Main() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {

        CustomSwitch(
            height = 70.dp,
            width = 140.dp,
            circleButtonPadding = 4.dp,
            outerBackgroundOnResource = R.drawable.switch_body_night,
            outerBackgroundOffResource = R.drawable.switch_body_day,
            circleBackgroundOnResource = R.drawable.switch_btn_moon,
            circleBackgroundOffResource = R.drawable.switch_btn_sun,
            stateOn = 1,
            stateOff = 0,
            initialValue = 0,
            onCheckedChanged = {}
        )

        Divider(thickness = 15.dp, color = MaterialTheme.colorScheme.surface)

        CustomSwitch(
            height = 70.dp,
            width = 140.dp,
            circleButtonPadding = 4.dp,
            outerBackgroundOnResource = R.drawable.switch_body_night,
            outerBackgroundOffResource = R.drawable.switch_body_day,
            circleBackgroundOnResource = R.drawable.switch_btn_moon,
            circleBackgroundOffResource = R.drawable.switch_btn_sun,
            stateOn = 1,
            stateOff = 0,
            initialValue = 1,
            onCheckedChanged = {}
        )

        Divider(thickness = 20.dp, color = MaterialTheme.colorScheme.surface)


        CustomSwitch(
            height = 70.dp,
            width = 140.dp,
            circleButtonPadding = 4.dp,
            outerBackgroundOnResource = R.drawable.switch_body_lemon,
            outerBackgroundOffResource = R.drawable.switch_body_orange,
            circleBackgroundOnResource = R.drawable.switch_btn_fruit_lemon,
            circleBackgroundOffResource = R.drawable.switch_btn_fruit_orange,
            stateOn = 1,
            stateOff = 0,
            initialValue = 0,
            onCheckedChanged = {}
        )

        Divider(thickness = 15.dp, color = MaterialTheme.colorScheme.surface)

        CustomSwitch(
            height = 70.dp,
            width = 140.dp,
            circleButtonPadding = 4.dp,
            outerBackgroundOnResource = R.drawable.switch_body_lemon,
            outerBackgroundOffResource = R.drawable.switch_body_orange,
            circleBackgroundOnResource = R.drawable.switch_btn_fruit_lemon,
            circleBackgroundOffResource = R.drawable.switch_btn_fruit_orange,
            stateOn = 1,
            stateOff = 0,
            initialValue = 1,
            onCheckedChanged = {}
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


@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun CustomSwitch(
    height: Dp,
    width: Dp,
    circleButtonPadding: Dp,
    outerBackgroundOnResource: Int,
    outerBackgroundOffResource: Int,
    circleBackgroundOnResource: Int,
    circleBackgroundOffResource: Int,
    stateOn: Int,
    stateOff: Int,
    initialValue: Int,
    onCheckedChanged: (checked: Boolean) -> Unit
) {

    val swipeableState = rememberSwipeableState(
        initialValue = initialValue,
        confirmStateChange = { newState ->
            if (newState == stateOff) {
                onCheckedChanged(false)
            } else {
                onCheckedChanged(true)
            }
            true
        }
    )

    val sizePx = with(LocalDensity.current) { (width - height).toPx() }
    val anchors = mapOf(0f to stateOff, sizePx to stateOn) // Maps anchor points (in px) to states

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
                if (swipeableState.currentValue == stateOff) Modifier.paint(
                    painterResource(id = outerBackgroundOffResource),
                    contentScale = ContentScale.FillBounds
                ) else Modifier.paint(
                    painterResource(id = outerBackgroundOnResource),
                    contentScale = ContentScale.FillBounds
                )
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Box(
            Modifier
                .offset { IntOffset(swipeableState.offset.value.roundToInt(), 0) }
                .size(height)
                .padding(circleButtonPadding)
                .clip(RoundedCornerShape(50))
                .then(
                    if (swipeableState.currentValue == stateOff) Modifier.paint(
                        painterResource(id = circleBackgroundOffResource),
                        contentScale = ContentScale.FillBounds
                    ) else Modifier.paint(
                        painterResource(id = circleBackgroundOnResource),
                        contentScale = ContentScale.FillBounds
                    )
                )
                .clickable {
                    scope.launch {

                        if (swipeableState.currentValue == stateOff) {
                            swipeableState.animateTo(stateOn)
                        } else {
                            swipeableState.animateTo(stateOff)
                        }

                    }


                }
        )
    }


}


@Preview
@Composable
fun CustomSwitchPreviewOff() {
    MyApplicationTheme {
        CustomSwitch(
            height = 70.dp,
            width = 140.dp,
            circleButtonPadding = 4.dp,
            outerBackgroundOnResource = R.drawable.switch_body_night,
            outerBackgroundOffResource = R.drawable.switch_body_day,
            circleBackgroundOnResource = R.drawable.switch_btn_moon,
            circleBackgroundOffResource = R.drawable.switch_btn_sun,
            stateOn = 1,
            stateOff = 0,
            initialValue = 0,
            onCheckedChanged = {}
        )
    }

}

@Preview
@Composable
fun CustomSwitchPreviewOn() {
    MyApplicationTheme {
        CustomSwitch(
            height = 70.dp,
            width = 140.dp,
            circleButtonPadding = 4.dp,
            outerBackgroundOnResource = R.drawable.switch_body_night,
            outerBackgroundOffResource = R.drawable.switch_body_day,
            circleBackgroundOnResource = R.drawable.switch_btn_moon,
            circleBackgroundOffResource = R.drawable.switch_btn_sun,
            stateOn = 1,
            stateOff = 0,
            initialValue = 1,
            onCheckedChanged = {}
        )
    }

}



