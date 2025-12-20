package com.example.kraftor.data.local

import android.content.Context
import androidx.core.content.edit

/**
 * class for the storing and fetching the user token for login and signup.
 * Also deletes the token for logout
 * @param -> context: Context (pass application context to prevent redundant calls / memory leaks)
 * @functions -> saveToken(token: String) -> saves auth token
 * @functions -> getToken() -> fetches auth token
 * @functions -> clearToken() -> deletes auth token from user device
 * */
class TokenStorage(context: Context) {

    private val prefs = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    // lambda to add token to prefs with key as auth_token (signup/login)
    fun saveToken(token: String) = prefs.edit { putString("auth_token", token) }

    // lambda to get auth token from prefs (startup auto call)
    fun getToken(): String? = prefs.getString("auth_token", null)

    // function to remove auth token from prefs (logout)
    fun clearToken() = prefs.edit{ remove("auth_token") }

}