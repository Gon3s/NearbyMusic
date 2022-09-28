package com.gones.nearbymusic.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.gones.nearbymusic.R
import com.gones.nearbymusic.ui._navigation.Screens
import com.gones.nearbymusic.ui._common.AlreadyAccountScreen
import com.gones.nearbymusic.ui.home.HomeViewModel

@Composable
fun PresentationScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(Unit) {
        if (viewModel.user != null) {
            navController.popBackStack()
            navController.navigate(Screens.Home.route)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Trouves les concerts proche de chez toi",
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Sélectionnes tes artistes préférés et par à leurs rencontre vers chez toi.",
            fontSize = 16.sp,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f),
        )

        Spacer(modifier = Modifier.height(48.dp))

        Image(
            painterResource(
                id = R.drawable.home_image
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .weight(1f)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                navController?.navigate(Screens.Register.route)
            },
            modifier = Modifier
                .fillMaxWidth()
                .align(CenterHorizontally)
        ) {
            Text(
                text = "Nouvel utilisateur",
                fontSize = 18.sp,
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        AlreadyAccountScreen(navController)
    }
}