package com.karevksy.notes.features.auth.signUp.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.karevksy.core.ui.components.DefaultButton
import com.karevksy.core.ui.components.ScreenLoader
import com.karevksy.core.ui.components.TextButton
import com.karevksy.core.utils.Screens
import com.karevksy.core.utils.observeEvent
import com.karevksy.notes.R
import com.karevksy.notes.features.auth.components.EmailTextField
import com.karevksy.notes.features.auth.components.PasswordTextField
import com.karevksy.notes.features.auth.signUp.SignUpViewModel

@Composable
fun SignUpScreen(viewModel: SignUpViewModel, navController: NavController) {
    viewModel.navigateToSignIn.observeEvent {
        navController.navigate(Screens.SignInScreen.route)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(start = 20.dp, end = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontFamily = FontFamily.Monospace,
                fontSize = 28.sp,
                text = stringResource(R.string.sign_up),
                fontWeight = FontWeight.Bold
            )

            UserInput(viewModel = viewModel)

            DefaultButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                onClick = { viewModel.registerNewUser() },
                title = "Регистрация",
                enabled = viewModel.buttonVisible
            )
            TextButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                onClick = { viewModel.navigateToSignIn() },
                title = stringResource(id = R.string.sign_in)
            )
        }
        if (viewModel.loading) {
            ScreenLoader()
        }
    }
}

@Composable
private fun UserInput(
    modifier: Modifier = Modifier,
    viewModel: SignUpViewModel
) {
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
}
