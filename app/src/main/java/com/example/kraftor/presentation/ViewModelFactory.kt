package com.example.kraftor.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.kraftor.data.repository.GenerateImageRepo
import com.example.kraftor.data.repository.GenerateTextRepo
import com.example.kraftor.data.repository.LoginRepo
import com.example.kraftor.data.repository.ReviewRepo
import com.example.kraftor.data.repository.RewriteTextRepo
import com.example.kraftor.data.repository.SignupRepo
import com.example.kraftor.presentation.viewmodels.GenerateImageViewModel
import com.example.kraftor.presentation.viewmodels.GenerateTextViewModel
import com.example.kraftor.presentation.viewmodels.LoginViewModel
import com.example.kraftor.presentation.viewmodels.ReviewViewModel
import com.example.kraftor.presentation.viewmodels.RewriteViewModel
import com.example.kraftor.presentation.viewmodels.SignupViewModel

/**
 * A factory for creating ViewModels with constructor dependencies.
 * This class creates a ViewModel by providing it with the
 * necessary Repo.
 * @param generateTextRepo GenerateTextRepo class object
 * @param generateImageRepo GenerateImageRepo class object
 * @param rewriteRepo RewriteTextRepo class object
 * @param reviewRepo ReviewRepo class object
 * @param loginRepo LoginRepo class object
 * @param signupRepo SignupRepo class object
 * @return ViewModel class object
 */
class ViewModelFactory(
    private val generateTextRepo: GenerateTextRepo,
    private val generateImageRepo: GenerateImageRepo,
    private val rewriteRepo: RewriteTextRepo,
    private val reviewRepo: ReviewRepo,
    private val loginRepo: LoginRepo,
    private val signupRepo: SignupRepo
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(GenerateTextViewModel::class.java)) {
            // create and return an instance, passing the repository.
            @Suppress("UNCHECKED_CAST")
            return GenerateTextViewModel(generateTextRepo) as T
        }

        else if (modelClass.isAssignableFrom(GenerateImageViewModel::class.java)) {
            // create and return an instance, passing the repository.
            @Suppress("UNCHECKED_CAST")
            return GenerateImageViewModel(generateImageRepo) as T
        }

        else if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            // create and return an instance, passing the repository.
            @Suppress("UNCHECKED_CAST")
            return LoginViewModel(loginRepo) as T
        }

        else if (modelClass.isAssignableFrom(SignupViewModel::class.java)) {
            // create and return an instance, passing the repository.
            @Suppress("UNCHECKED_CAST")
            return SignupViewModel(signupRepo) as T
        }

        else if (modelClass.isAssignableFrom(ReviewViewModel::class.java)) {
            // create and return an instance, passing the repository.
            @Suppress("UNCHECKED_CAST")
            return ReviewViewModel(reviewRepo) as T
        }

        else if (modelClass.isAssignableFrom(RewriteViewModel::class.java)) {
            // create and return an instance, passing the repository.
            @Suppress("UNCHECKED_CAST")
            return RewriteViewModel(rewriteRepo) as T
        }


        else throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}
