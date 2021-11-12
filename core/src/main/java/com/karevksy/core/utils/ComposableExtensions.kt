package com.karevksy.core.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Observer

@Composable
fun <T> LiveDataEvent<T>.observeEvent(observer: (T) -> Unit) {
    this.observe(LocalLifecycleOwner.current, observer)
}