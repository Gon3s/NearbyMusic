package com.gones.nearbymusic.ui._common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.gones.nearbymusic.ui._navigation.Screens

@Composable
fun AlreadyAccountScreen(
    navController: NavController?
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Tu as déjà un compte ?",
            fontSize = 14.sp,
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
        )
        Spacer(modifier = Modifier
            .size(4.dp))
        Text(
            text = "Connecte-toi",
            fontSize = 14.sp,
            color = MaterialTheme.colors.primary,
            modifier = Modifier
                .clickable {
                    navController?.navigate(Screens.Login.route)
                }
        )
    }
}