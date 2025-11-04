package com.abdulmateen.startertemplate.feature.main.dashboard.chat.conversation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.ArrowCircleRight
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.abdulmateen.startertemplate.feature.main.dashboard.chat.conversation.componenets.ConversationContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationScreen(
    navController: NavController
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Gianluca")
                },
                navigationIcon = {
                    Icon(
                        imageVector = Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        modifier = Modifier.clickable(
                            onClick = { navController.navigateUp() }
                        )
                    )
                },
                actions = {
                    Icon(
                        imageVector = Icons.Default.MoreHoriz,
                        contentDescription = "Back",
                        modifier = Modifier.clickable(
                            onClick = {}
                        )
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        },
        content = {innerPadding ->
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(innerPadding)
            ) {
                ImageNameRow(
                    modifier = Modifier.fillMaxWidth()
                        .background(color = MaterialTheme.colorScheme.primary)
                )

                LazyColumn(
                    modifier = Modifier.weight(.1f)
                ) {
                    items(count = 2){
                        ConversationContainer(
                            messageText = "Hello",
                            time = "12:00",
                            isOut = false,
                            isSeen = false

                        )
                    }
                }

            }
        },
        bottomBar = {
            BottomBarSection()
        }
    )
}

@Composable
private fun BottomBarSection() {
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(16.dp)
        ) {
            var textField by rememberSaveable { mutableStateOf("") }
            OutlinedTextField(
                value = textField,
                onValueChange = { textField = it },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = White,
                    unfocusedLabelColor = White
                ),
                modifier = Modifier.weight(.1f)
            )
            IconButton(
                onClick = {}
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowCircleRight,
                    contentDescription = "Next"
                )
            }
        }
    }
}


@Composable
private fun ImageNameRow(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
    ) {
        Image(
            imageVector = Icons.Default.Person,
            contentDescription = "User"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Name",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Preview
@Composable
private fun ConversationScreenPreview() {
    ConversationScreen(
        navController = rememberNavController()
    )
}