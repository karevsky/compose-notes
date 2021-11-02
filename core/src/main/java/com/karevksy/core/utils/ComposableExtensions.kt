package com.karevksy.core.utils

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner

@Composable
fun LiveEvent.observeEvent(observer: (Unit) -> Unit) {
    this.observe(LocalLifecycleOwner.current, observer)
}