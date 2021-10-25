package com.karevksy.core.extensions

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.ui.platform.LocalContext
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


fun <T> Single<T>.androidAsync(subscribeOn: Scheduler = Schedulers.io()): Single<T> =
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(subscribeOn)

fun Completable.androidAsync(subscribeOn: Scheduler = Schedulers.io()): Completable =
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(subscribeOn)

fun <T> Observable<T>.androidAsync(subscribeOn: Scheduler = Schedulers.io()): Observable<T> =
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(subscribeOn)

fun Disposable.addToDisposable(disposable: CompositeDisposable) {
    disposable.add(this)
}
