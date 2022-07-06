package de.htw_berlin.quiz.fragments.score

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.htw_berlin.quiz.data.userDao

/**
 * Viewmodelfactory for creating viewmodel  with parameters
 */

class ScoreViewModelFactory (private val dataSource: userDao,
                             private val application: Application): ViewModelProvider.Factory
{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
            return ScoreViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}