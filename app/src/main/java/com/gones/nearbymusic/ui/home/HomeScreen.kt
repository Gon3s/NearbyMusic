package com.gones.nearbymusic.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gones.nearbymusic.ui._navigation.Screens

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavController? = null
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Bonjour ${viewModel.user?.displayName}"
        )

        Spacer(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1f)
        )

        Button(
            onClick = {
                viewModel.logout()
                navController?.popBackStack()
                navController?.navigate(Screens.Presentation.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "Se déconnecter",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
    }
}