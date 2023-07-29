package com.abdulmateen.startertemplate.presentation.screens.main.requests

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.shimmer
import com.google.accompanist.placeholder.placeholder
import kotlinx.coroutines.flow.MutableSharedFlow

@Composable
fun RequestsScreen(
    navController: NavController,
    uiState: RequestsUIState,
    uiEvents: (RequestsScreenUIEvents) -> Unit,
    apiEvents: MutableSharedFlow<RequestsApiEvents>
) {
    val context = LocalContext.current
    Scaffold() { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
            ) {
                items(100) {
                    RequestListItem(
                        imageUrl = "https://images.unsplash.com/photo-1670250492416-570b5b7343b1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1325&q=80",
                        title = "Massimo10 Try ag",
                        distance = "500 KM",
                        description = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, " +
                                "when an unknown printer took a galley of type and scrambled it to make a type specimen book. " +
                                "It has survived not only five centuries, but also the leap into electronic typesetting, " +
                                "remaining essentially unchanged. It was popularised in the 1960s " +
                                "with the release of Letraset sheets containing Lorem Ipsum passages, " +
                                "and more recently with desktop publishing " +
                                "software like Aldus PageMaker including versions of Lorem Ipsum.",
                        price = "€2000",
                        onClick = {}
                    )
                }
            }
//            EmptyDataList()
        }
    }

    LaunchedEffect(key1 = true){
        apiEvents.collect{event ->
            when(event){
                RequestsApiEvents.OnSuccess -> {}
                is RequestsApiEvents.PopUpErrorMessage -> {
                    Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RequestListItem(
    imageUrl: String,
    title: String,
    distance: String,
    description: String,
    price: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.padding(vertical = 4.dp),
        onClick = onClick
    ) {
        ConstraintLayout(modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            val (image, titleText, distanceText, descriptionText, priceText) = createRefs()
            val painter = rememberAsyncImagePainter(model = imageUrl)
            Image(
                painter = painter,
                contentDescription = "RequestImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .placeholder(
                        visible = painter.state is AsyncImagePainter.State.Loading,
                        highlight = PlaceholderHighlight.shimmer(),
                        color = LightGray
                    )
                    .clip(shape = RoundedCornerShape(8.dp))
                    .size(110.dp)
                    .constrainAs(image) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    }
            )
            Text(text = title,
                style = MaterialTheme.typography.titleSmall.copy(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .constrainAs(titleText) {
                        top.linkTo(parent.top)
                        start.linkTo(image.end, margin = 4.dp)
                        end.linkTo(distanceText.start, margin = 2.dp)
                        width = Dimension.fillToConstraints
                    }
//                    .placeholder(
//                        visible = true,
//                        highlight = PlaceholderHighlight.shimmer(),
//                        color = LightGray
//                    )
            )
            Text(
                text = distance,
                style = MaterialTheme.typography.labelSmall.copy(color = MaterialTheme.colorScheme.onSurface),
                modifier = Modifier
                    .constrainAs(distanceText) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(titleText.bottom)
                    }
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall.copy(color = MaterialTheme.colorScheme.onSurface, textAlign = TextAlign.Justify),
                modifier = Modifier
                    .constrainAs(descriptionText) {
                        start.linkTo(image.end, margin = 4.dp)
                        top.linkTo(titleText.bottom, margin = 4.dp)
                        end.linkTo(parent.end)
                        bottom.linkTo(priceText.top, margin = 4.dp)
                        height = Dimension.fillToConstraints
                        width = Dimension.fillToConstraints
                    }
//                    .placeholder(
//                        visible = true,
//                        highlight = PlaceholderHighlight.shimmer(),
//                        color = LightGray
//                    )
            , overflow = TextOverflow.Ellipsis
            )
            Text(
                text = price,
                style = MaterialTheme.typography.labelLarge.copy(color = MaterialTheme.colorScheme.onSurface, fontWeight = FontWeight.Bold),
                modifier = Modifier
                    .constrainAs(priceText) {
                        start.linkTo(image.end, margin = 4.dp)
                        bottom.linkTo(image.bottom)
                    }
//                    .placeholder(
//                        visible = true,
//                        highlight = PlaceholderHighlight.shimmer(),
//                        color = LightGray
//                    )
            )
        }
    }
}


@Preview(
    showBackground = true,
    backgroundColor = 0xFFEFEDE9
)
@Composable
fun RequestsScreenPreview() {
    RequestsScreen(
        navController = rememberNavController(),
        apiEvents = MutableSharedFlow(),
        uiEvents = {},
        uiState = RequestsUIState()
    )
}