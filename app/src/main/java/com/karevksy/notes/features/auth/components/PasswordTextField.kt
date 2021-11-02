package com.karevksy.notes.features.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.karevksy.core.utils.Constants
import com.karevksy.core.utils.InputWrapper

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    input: InputWrapper,
    onValueChange: (String) -> Unit,
    label: String = "Пароль",
    errorTextModifier: Modifier = Modifier.padding(start = 20.dp)
) {
    var passwordHidden by rememberSaveable { mutableStateOf(true) }

    Column {
        TextField(
            modifier = modifier,
            value = input.input,
            onValueChange = { value -> onValueChange(value) },
            label = { Text(label) },
            singleLine = true,
            isError = input.isError(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {
                IconButton(onClick = { passwordHidden = passwordHidden.not() }) {
                    val visibilityIcon =
                        if (passwordHidden) Icons.Filled.VisibilityOff
                        else Icons.Filled.Visibility
                    Icon(imageVector = visibilityIcon, contentDescription = "")
                }
            },
            visualTransformation = if (passwordHidden) PasswordVisualTransformation()
            else VisualTransformation.None
        )
        Text(
            modifier = errorTextModifier,
            text = if (input.isError()) input.error!! else Constants.EMPTY_STRING,
            color = MaterialTheme.colors.error,
            fontSize = 12.sp
        )
    }
}