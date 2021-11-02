package com.karevksy.notes.features.auth.signIn.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.karevksy.core.ui.components.DefaultButton
import com.karevksy.core.ui.components.ScreenLoader
import com.karevksy.core.ui.components.TextButton
import com.karevksy.core.utils.observeEvent
import com.karevksy.notes.R
import com.karevksy.notes.features.auth.components.EmailTextField
import com.karevksy.notes.features.auth.components.PasswordTextField
import com.karevksy.notes.features.auth.signIn.SignInViewModel
import com.karevksy.notes.features.util.Screens

@Composable
fun SignInScreen(
    viewModel: SignInViewModel,
    navController: NavController
) {
    viewModel.navigateToNotes.observeEvent {
        navController.navigate(Screens.NotesScreen.route)
    }

    viewModel.navigateToSignUp.observeEvent {
        navController.navigate(Screens.SignUpScreen.route)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                fontFamily = FontFamily.Monospace,
                fontSize = 28.sp,
                text = stringResource(R.string.notes),
                fontWeight = FontWeight.Bold
            )

            UserInput(viewModel = viewModel)
            SignInButtons(viewModel = viewModel)
        }

        if (viewModel.loading) {
            ScreenLoader()
        }

        SignUpText(
            modifier = Modifier
                .padding(20.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .clickable { viewModel.navigateToSignUp() }
        )
    }
}

@Composable
fun SignUpText(modifier: Modifier = Modifier) {
    Text(
        buildAnnotatedString {
            append("В первый раз? ")
            withStyle(SpanStyle(textDecoration = TextDecoration.Underline)) {
                append("Зарегистрируйтесь")
            }
        },
        modifier = modifier,
        color = MaterialTheme.colors.primaryVariant,
        textAlign = TextAlign.Center
    )
}

@Composable
fun UserInput(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        EmailTextField(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            input = viewModel.email,
            onValueChanged = { viewModel.onEmailChanged(it) }
        )
        PasswordTextField(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
                .fillMaxWidth(),
            input = viewModel.password,
            onValueChange = { viewModel.onPasswordChanged(it) }
        )
    }
}

@Composable
fun SignInButtons(
    modifier: Modifier = Modifier,
    viewModel: SignInViewModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        DefaultButton(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                .fillMaxWidth(),
            onClick = { viewModel.signInUser() },
            title = stringResource(R.string.sign_in),
            enabled = viewModel.buttonVisible
        )
        TextButton(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                .fillMaxWidth(),
            onClick = { viewModel.signInLocal() },
            title = stringResource(R.string.sign_in_local),
            enabled = true,
        )
    }
}

