package com.abdulmateen.startertemplate.presentation.screens.main.chat.conversation.componenets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


private val AuthorChatBubble =
    RoundedCornerShape(topStart = 16.dp, topEnd = 8.dp, bottomStart = 0.dp, bottomEnd = 8.dp)
private val UserChatBubble =
    RoundedCornerShape(topStart = 8.dp, topEnd = 16.dp, bottomStart = 8.dp, bottomEnd = 0.dp)


@Composable
fun ConversationContainer(
    messageText: String,
    time: String,
    isOut: Boolean,
    isSeen: Boolean
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = if (isOut) Alignment.End else Alignment.Start
    ) {
        if (messageText.isNotEmpty()) {
            Row(
                verticalAlignment = Alignment.Bottom
            ){
                if (!isOut) {
                Image(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Person"
                )
            }

                Box(
                    modifier = Modifier
                        .padding(start = if (isOut) 75.dp else 1.dp, end = if (!isOut) 75.dp else 1.dp)
                        .background(
                            if (isOut) Black else Red,
                            shape = if (isOut) UserChatBubble else AuthorChatBubble
                        )
                        .padding(8.dp)
                ) {
                    Text(text = messageText, color = White)
                }
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (isOut) {
                Icon(
                    imageVector = if (isSeen) Icons.Default.DoneAll else Icons.Default.Done,
                    contentDescription = "SeenTick",
                    tint = if (isSeen) Green else LightGray,
                    modifier = Modifier.size(14.dp)
                )
            }
            Spacer(modifier = Modifier.width(6.dp))
            Text(text = time, fontSize = 11.sp, color = Black)
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ConversationContainerPreview() {
    Column {
        ConversationContainer(
            messageText = "Hello World kfjadsklja kj kljdkflasj klk \n" +
                    "lakkdfj k jakljk",
            time = "",
            isOut = false,
            isSeen = false,

            )
        ConversationContainer(
            messageText = "Hello World kfjadsklja kj kljdkflasj klk \n" +
                    "lakkdfj k jakljk",
            time = "",
            isOut = true,
            isSeen = false,

            )
    }

}
