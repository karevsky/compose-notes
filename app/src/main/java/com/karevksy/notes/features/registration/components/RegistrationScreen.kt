package com.karevksy.notes.features.registration.components

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.karevksy.notes.features.registration.RegistrationEvent
import com.karevksy.notes.features.registration.RegistrationViewModel

@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel(),
    navController: NavController
) {
    var email by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .width(300.dp)
                .align(Alignment.Center)
        ) {
            TextField(
                value = email,
                onValueChange = { value -> email = value },
                label = { Text("Почта") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )
            TextField(
                value = password,
                onValueChange = { value -> password = value },
                label = { Text("Пароль") },
                singleLine = true,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    IconButton(onClick = { passwordVisibility = !passwordVisibility }) {
                        val visibilityIcon =
                            if (passwordVisibility) Icons.Filled.Visibility
                            else Icons.Filled.VisibilityOff
                        Icon(imageVector = visibilityIcon, contentDescription = "")
                    }
                }
            )
            Button(
                onClick = { viewModel.onEvent(RegistrationEvent.OnRegisterNewUserEvent(email, password)) },
                modifier = Modifier
                    .padding(top = 20.dp)
                    .height(40.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Регистрация")
            }
        }
    }
}