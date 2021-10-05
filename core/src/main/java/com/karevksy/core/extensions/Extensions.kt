package com.karevksy.core.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers


fun <T> Single<T>.androidAsync(subscribeOn: Scheduler = Schedulers.io()): Single<T> =
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(subscribeOn)

fun Completable.androidAsync(subscribeOn: Scheduler = Schedulers.io()): Completable =
    this.observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(subscribeOn)
