package com.example.kraftor.backend.network

import java.lang.ThreadLocal

/**
 * A thread-safe store to hold the category of the current API request.
 * This allows our Moshi adapter factory to know which type of response to deserialize
 * without needing access to the request object itself.
 */
object CategoryStore {
    // Each thread gets its own copy of the category string
    private val store = ThreadLocal<String>()

    fun set(category: String) {
        store.set(category)
    }

    fun get(): String? {
        return store.get()
    }

    fun clear() {
        store.remove()
    }
}