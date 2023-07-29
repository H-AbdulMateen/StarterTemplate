package com.abdulmateen.startertemplate.presentation.screens.main.my_bids

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.abdulmateen.startertemplate.R

@Composable
fun MyBidsScreen(
    navController: NavController
) {
    Scaffold() {innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)){
            Text(text = stringResource(id = R.string.bids))
        }
    }
}