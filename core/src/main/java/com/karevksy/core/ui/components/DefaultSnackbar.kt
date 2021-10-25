package com.karevksy.core.ui.components

import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@Composable
fun DefaultSnackbar(
    modifier: Modifier = Modifier,
    onClickEvent: () -> Unit,
    message: String
) {
    val snackState = remember { SnackbarHostState() }
    val snackScope = rememberCoroutineScope()

    SnackbarHost(hostState = snackState, modifier)

    LaunchedEffect(snackState) {
        snackScope.launch {
            snackState.showSnackbar(
                message = message,
                "Отмена"
            ).also { result ->
                if (result == SnackbarResult.ActionPerformed) onClickEvent()
            }
        }
    }
}