package com.karevksy.notes.features.auth.registration.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.karevksy.core.ui.components.DefaultButton
import com.karevksy.core.ui.components.DefaultLoader
import com.karevksy.notes.features.auth.components.EmailTextField
import com.karevksy.notes.features.auth.components.PasswordTextField
import com.karevksy.notes.features.auth.registration.SignUpState
import com.karevksy.notes.features.auth.registration.SignUpViewModel
import com.karevksy.notes.features.util.Screens

@Composable
fun SignUpScreen(viewModel: SignUpViewModel, navController: NavController) {
    val uiState = viewModel.uiState.observeAsState(initial = SignUpState()).value

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .align(Alignment.TopCenter)
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                text = "Регистрация",
            )
            EmailTextField(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .fillMaxWidth(),
                input = viewModel.email,
                onValueChanged = { viewModel.onEmailChanged(it) },
            )
            PasswordTextField(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                input = viewModel.password,
                onValueChange = { viewModel.onPasswordChanged(it) },
            )
            PasswordTextField(
                modifier = Modifier
                    .padding(top = 5.dp)
                    .fillMaxWidth(),
                input = viewModel.repeatPassword,
                onValueChange = { viewModel.onRepeatPasswordChanged(it) },
                label = "Повторите пароль"
            )
            if (uiState.loading) {
                DefaultLoader(modifier = Modifier.padding(top = 20.dp))
            }
            if (uiState.successSignUp) {
                navController.navigate(Screens.AuthenticationScreen.route)
            }
        }
        DefaultButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.BottomCenter),
            onClick = { viewModel.registerNewUser() },
            title = "Регистрация",
            enabled = uiState.buttonVisible
        )
    }
}
