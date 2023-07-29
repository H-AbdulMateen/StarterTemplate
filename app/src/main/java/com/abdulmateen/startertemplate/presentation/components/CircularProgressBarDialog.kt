package com.abdulmateen.startertemplate.presentation.components

import android.content.res.Configuration
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.abdulmateen.startertemplate.R
import com.abdulmateen.startertemplate.presentation.ui.theme.SelfeeTheme
import kotlinx.coroutines.launch

@Composable
fun CircularProgressBarDialog(loadingStatus: Boolean) {
    if (loadingStatus) {
        Dialog(
            onDismissRequest = {},
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(Color.DarkGray.copy(alpha = 0.6f), shape = RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
//                    CircularProgressIndicator(color = redCustom)
                    LoadingWheel(contentDesc = "Loading")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(id = R.string.loading_dots),
                        color = Color.White
                    )
                }
                0            }
        }
    }
}

@Composable
fun LoadingWheel(
    contentDesc: String,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition()

    // Specifies the float animation for slowly drawing out the lines on entering
    val floatAnimValues = (0 until NUM_OF_LINES).map { remember { Animatable(1F) } }
    LaunchedEffect(floatAnimValues) {
        (0 until NUM_OF_LINES).map { index ->
            launch {
                floatAnimValues[index].animateTo(
                    targetValue = 0F,
                    animationSpec = tween(
                        durationMillis = 100,
                        easing = FastOutSlowInEasing,
                        delayMillis = 40 * index
                    )
                )
            }
        }
    }

    // Specifies the rotation animation of the entire Canvas composable
    val rotationAnim by infiniteTransition.animateFloat(
        initialValue = 0F,
        targetValue = 360F,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = ROTATION_TIME, easing = LinearEasing)
        )
    )

    // Specifies the color animation for the base-to-progress line color change
    val baseLineColor = Color(0XFF9E9E9E)
    val progressLineColor = Color.White
    val colorAnimValues = (0 until NUM_OF_LINES).map { index ->
        infiniteTransition.animateColor(
            initialValue = baseLineColor,
            targetValue = baseLineColor,
            animationSpec = infiniteRepeatable(
                animation = keyframes {
                    durationMillis = ROTATION_TIME / 2
                    progressLineColor at ROTATION_TIME / NUM_OF_LINES / 2 with LinearEasing
                    baseLineColor at ROTATION_TIME / NUM_OF_LINES with LinearEasing
                },
                repeatMode = RepeatMode.Restart,
                initialStartOffset = StartOffset(ROTATION_TIME / NUM_OF_LINES / 2 * index)
            )
        )
    }

    // Draws out the LoadingWheel Canvas composable and sets the animations
    Canvas(
        modifier = modifier
            .size(48.dp)
            .padding(8.dp)
            .graphicsLayer { rotationZ = rotationAnim }
            .semantics { contentDescription = contentDesc }
    ) {
        repeat(NUM_OF_LINES) { index ->
            rotate(degrees = index * 30f) {
                drawLine(
                    color = colorAnimValues[index].value,
                    // Animates the initially drawn 1 pixel alpha from 0 to 1
                    alpha = if (floatAnimValues[index].value < 1f) 1f else 0f,
                    strokeWidth = 4F,
                    cap = StrokeCap.Round,
                    start = Offset(size.width / 2, size.height / 4),
                    end = Offset(size.width / 2, floatAnimValues[index].value * size.height / 4)
                )
            }
        }
    }
}

@Preview(
    name = "Loading Wheel Light Preview",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
)
@Preview(
    name = "Loading Wheel Dark Preview",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
fun LoadingWheelPreview() {
    SelfeeTheme {
        Surface {
            LoadingWheel(contentDesc = "LoadingWheel")
        }
    }
}

private const val ROTATION_TIME = 12000
private const val NUM_OF_LINES = 12