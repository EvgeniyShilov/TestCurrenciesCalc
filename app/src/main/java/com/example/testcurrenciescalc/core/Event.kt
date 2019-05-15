package com.example.testcurrenciescalc.core

class Event<T>(val content: T? = null) {

    private var hasBeenHandled: Boolean = false

    fun getContentIfNotHandled(): T? =
        if (hasBeenHandled) null else {
            hasBeenHandled = true
            content
        }

    fun handleIfNotHandled(): Boolean =
        if (hasBeenHandled) false else {
            hasBeenHandled = true
            true
        }
}
