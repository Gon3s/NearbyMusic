package com.gones.nearbymusic.ui.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.gones.nearbymusic.ui._common.AlreadyAccountScreen
import com.gones.nearbymusic.ui._common.EmailTextField
import com.gones.nearbymusic.ui._common.NameTextField
import com.gones.nearbymusic.ui._common.PasswordTextField
import com.gones.nearbymusic.ui._navigation.Screens
import kotlinx.coroutines.flow.collectLatest

@Preview(
    name = "Light Mode",
    showBackground = true
)
@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = hiltViewModel(),
    navController: NavController? = null,
) {
    val uiState by viewModel.uiState
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when(event) {
                is RegisterViewModel.UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
                is RegisterViewModel.UiEvent.RegisterOk -> {
                    navController?.navigate(Screens.Home.route) {
                        popUpTo(Screens.Presentation.route) {
                            inclusive = true
                        }
                    }
                }
                else -> {

                }
            }
        }
    }

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null,
                Modifier
                    .size(32.dp)
                    .clickable {
                        navController?.navigateUp()
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Création de compte",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Crée toi un compte pour retrouver tes artistes favoris",
                fontSize = 16.sp,
                color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(36.dp))

            NameTextField(
                value = uiState.name,
                onValueChange = viewModel::onNameChange,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            EmailTextField(
                value = uiState.email,
                onValueChange = viewModel::onEmailChange,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            PasswordTextField(
                value = uiState.password,
                onValueChange = viewModel::onPasswordChange,
                modifier = Modifier.fillMaxWidth(),
                onGo = viewModel::register
            )

            Spacer(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            )

            Button(
                onClick = viewModel::register,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Text(
                    text = "Suivant",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))
            AlreadyAccountScreen(navController)
        }
    }
}