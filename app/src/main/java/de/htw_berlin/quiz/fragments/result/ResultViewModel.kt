package de.htw_berlin.quiz.fragments.result

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import de.htw_berlin.quiz.data.User
import de.htw_berlin.quiz.data.userDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ResultViewModel(rname: String, rscore: Int, val database: userDao,application: Application)
    : AndroidViewModel(application)
{
    /**Event
     *
     *LiveData is an observable data holder class that is used
     * to observe the changes of a ViewModel and update those changes.
     */

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    private val _sname = MutableLiveData<String>()
    val sname: LiveData<String>
        get() = _sname

    private val _eventEndGame = MutableLiveData<Boolean>()
    val endGame: LiveData<Boolean>
        get() = _eventEndGame


    /**
     * new instance of User class
     */
    val new_user = User(0, rname , rscore )


    /**
     * set LiveData name and score to values from constructor
     */
    init {
        _sname.value = rname
        _score.value = rscore
    }


    /**
     * starts when button_ende clicked from fragment_result.xml
     */
    fun onEnd()
    {
        /**
         * insert new User to Database if score != 0
         */

        if(new_user.punkte != 0)
        {
            insert(new_user)
        }
        else
        {
            Toast.makeText(getApplication(),"Ergebnis nicht gespeichet !", Toast.LENGTH_SHORT).show()
        }


        /**
         * trigger event EndGame to navigate to title fragment
         */
        _eventEndGame.value = true
    }


    /**
     * CoroutineScope
     * insert user in Database
     */
    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO)
    {

        /**
         * find and if exists delete old users with same score
         */
        val userWithSamePoints : List<User> = database.getUserByPoints(user.punkte)

        if(userWithSamePoints.isNotEmpty())
        {
            for (uwsp in userWithSamePoints)
            {
                database.deleteUserById(uwsp.id)
            }
        }

        /**
         * insert current user in Database
         */
        database.insert(user)
    }
}