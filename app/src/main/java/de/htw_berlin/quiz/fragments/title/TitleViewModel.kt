package de.htw_berlin.quiz.fragments.title



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel



class TitleViewModel : ViewModel() {


    /**Event with Livedata to TitleFragment
     *
     *LiveData is an observable data holder class that is used
     * to observe the changes of a ViewModel and update those changes.
     */
    private val _eventgameStart = MutableLiveData<Boolean>()
    val eventGameStart: LiveData<Boolean>
    get() = _eventgameStart

    private val _eventWatchScore = MutableLiveData<Boolean>()
    val eventWatchScore: LiveData<Boolean>
        get() = _eventWatchScore


    /**
     * onGameStart from fragment_xml
     *
     * waiting for user input
     */

    fun onGameStart()
    {
        _eventgameStart.value = true
    }

    /**
     * starts whe user clicks on score button ( TOP 5 )
     */

    fun onUseScore()
    {
        _eventWatchScore.value = true
    }

}