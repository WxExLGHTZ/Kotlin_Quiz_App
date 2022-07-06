package de.htw_berlin.quiz.fragments.result

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import de.htw_berlin.quiz.data.userDao

class ResultViewModelFactory (
    private val rname: String,
    private val rscore: Int,
    private val dataSource: userDao,
private val application: Application): ViewModelProvider.Factory

{

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(rname, rscore, dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}