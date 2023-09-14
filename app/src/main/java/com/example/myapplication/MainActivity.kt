package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AnchoredDraggableState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme

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



@Composable
fun Main() {

    val height = 18.dp
    val width = 48.dp

    Box(
        modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Cyan)
            .border(1.dp, Color.DarkGray, CircleShape)

    ) {


        val state = remember { AnchoredDraggableState }
        val density = LocalDensity.current
        val anchors = with (density) {
            DraggableAnchors {
                Start at -100.dp.toPx()
                Center at 0f
                End at 100.dp.toPx()
            }
        }
        SideEffect {
            state.updateAnchors(anchors)
        }
        Box(
            Modifier.offset { IntOffset(x = state.requireOffset(), y = 0) }
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
