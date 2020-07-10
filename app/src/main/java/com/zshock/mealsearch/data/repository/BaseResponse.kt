package com.zshock.mealsearch.data.repository

public class BaseResponse<T> {
    var data: T? = null
    var error: Throwable? = null

    constructor(data: T?) {
        this.data = data
    }

    constructor(error: Throwable) {
        this.error = error
    }
}