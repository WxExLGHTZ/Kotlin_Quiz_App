package de.htw_berlin.quiz.fragments.question

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


/**
 * Viewmodelfactory for creating viewmodel  with parameters
 */


class QuestionViewModelFactory (private val name: String): ViewModelProvider.Factory{


    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuestionViewModel::class.java)) {
            return QuestionViewModel(name) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}