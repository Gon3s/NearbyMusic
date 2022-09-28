package com.gones.nearbymusic.ui._common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.gones.nearbymusic.R
import kotlin.reflect.KFunction0

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    onGo: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(false) }

    val icon = if (isVisible) painterResource(R.drawable.ic_visibility_on)
    else painterResource(R.drawable.ic_visibility_off)

    val visualTransformation = if (isVisible) VisualTransformation.None
    else PasswordVisualTransformation()

    val focusManager = LocalFocusManager.current

    TextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        placeholder = {
            Text("Mot de passe")
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = "Mot de passe",
                tint = MaterialTheme.colors.primary
            )
        },
        trailingIcon = {
            Icon(painter = icon, contentDescription = null, modifier = Modifier.clickable {
                isVisible = !isVisible
            })
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Go
        ),
        keyboardActions = KeyboardActions(
            onGo = { onGo() }),
        visualTransformation = visualTransformation,
        singleLine = true,
    )
}