package com.karevksy.notes.features.auth.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.sp
import com.karevksy.core.util.Constants
import com.karevksy.core.util.InputWrapper

@Composable
fun EmailTextField(
    modifier: Modifier = Modifier,
    input: InputWrapper,
    onValueChanged: (String) -> Unit,
    label: String = "Почта"
) {
    Column {
        TextField(
            modifier = modifier,
            value = input.input,
            onValueChange = { value -> onValueChanged(value) },
            label = { Text(label) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = input.isError()
        )
        Text(
            text = if (input.isError()) input.error!! else Constants.EMPTY_STRING,
            color = MaterialTheme.colors.error,
            fontSize = 12.sp
        )
    }
}