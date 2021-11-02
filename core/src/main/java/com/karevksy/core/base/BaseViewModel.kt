package com.karevksy.core.base

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable


abstract class BaseViewModel: ViewModel() {
    val compositeDisposable = CompositeDisposable()

    var loading by mutableStateOf(false)
}