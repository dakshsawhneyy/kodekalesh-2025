package com.example.kraftor.di

import android.content.Context
import com.example.kraftor.backend.network.RetrofitProvider
import com.example.kraftor.data.repository.*

/**
 * Dependency injection container for the app
 * holds instances of services and repositories used
 * ensuring there is only one instance of each.
 */
interface AppContainer {
    val generateTextRepo: GenerateTextRepo
    val generateImageRepo: GenerateImageRepo
    val rewriteRepo: RewriteTextRepo
    val reviewRepo: ReviewRepo
    val loginRepo: LoginRepo
    val signupRepo: SignupRepo
}

/**
 * The default implementation of the AppContainer.
 * @param context Context class
 */
class DefaultAppContainer(private val context: Context) : AppContainer {

    // Lazily create the APIService once when first accessed using RetrofitProvider.
    private val apiService by lazy {
        RetrofitProvider.createAPI(context)
    }

    // create each repository, providing the apiService instance.
    override val generateTextRepo: GenerateTextRepo by lazy {
        GenerateTextRepo(apiService)
    }

    override val generateImageRepo: GenerateImageRepo by lazy {
        GenerateImageRepo(apiService)
    }

    override val rewriteRepo: RewriteTextRepo by lazy {
        RewriteTextRepo(apiService)
    }

    override val reviewRepo: ReviewRepo by lazy {
        ReviewRepo(apiService)
    }

    override val loginRepo: LoginRepo by lazy {
        LoginRepo(apiService)
    }

    override val signupRepo: SignupRepo by lazy {
        SignupRepo(apiService)
    }
}
