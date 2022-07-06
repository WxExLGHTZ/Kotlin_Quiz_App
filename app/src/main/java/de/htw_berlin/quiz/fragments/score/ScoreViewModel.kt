package de.htw_berlin.quiz.fragments.score

import android.app.Application
import androidx.lifecycle.*
import de.htw_berlin.quiz.data.User
import de.htw_berlin.quiz.data.userDao
import kotlinx.coroutines.*

class ScoreViewModel(val database: userDao, application: Application) : AndroidViewModel(application)
{

    /**
     * user variable from Database
     */

    var users : List<User> = emptyList()


    /**Event
     *
     *LiveData is an observable data holder class that is used
     * to observe the changes of a ViewModel and update those changes.
     */

    private val _eventDeleteScores = MutableLiveData<Boolean>()
    val eventDeleteScores: LiveData<Boolean>
        get() = _eventDeleteScores


    private val _eventNewScore = MutableLiveData<List<User>>()
    val eventNewScore: LiveData<List<User>>
        get() = _eventNewScore


    /**
     * show score when initialized
     */

    init {
        showScores()
    }



    fun showScores()
    {

        /**
         * getting score from Database (userDao)
         */
        runBlocking {
            val u = async {
                getAll()
            }
            users = u.await()
        }


        /**
         * trigger event for ScoreFragment function checkNewScore
         * to show score list  (TOP 5)
         */
        _eventNewScore.value = users
    }


    /**
     * get data from database in background Thread
     */

    suspend fun getAll() : List<User>
    {
        var allUsers: List<User>

        withContext(Dispatchers.IO)
        {
            allUsers = database.getAllUsers()
        }

        return allUsers
    }



    /**
     * delete all scores when delete button clicked and trigger event for refreshing score fragment
     */
    fun deleteScores()
    {
        viewModelScope.launch (Dispatchers.IO)
        {
            database.delete()
        }
        _eventDeleteScores.value = true
    }
}