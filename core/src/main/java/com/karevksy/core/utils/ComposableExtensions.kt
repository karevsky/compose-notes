package com.karevksy.core.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun LiveEvent.observeEvent(observer: (@Composable Unit) -> Unit) {
    this.observe(LocalLifecycleOwner.current, observer)
}

@Composable
fun Unit.showToast(text: String) {
    Toast.makeText(LocalContext.current, text, Toast.LENGTH_SHORT).show()
}