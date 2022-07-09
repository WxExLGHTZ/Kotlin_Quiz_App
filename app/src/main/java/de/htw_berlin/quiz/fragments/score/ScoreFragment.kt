package de.htw_berlin.quiz.fragments.score

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import de.htw_berlin.quiz.R
import de.htw_berlin.quiz.data.User
import de.htw_berlin.quiz.data.userDatabase
import de.htw_berlin.quiz.databinding.FragmentScoreBinding


class ScoreFragment : Fragment() {



    internal lateinit var binding : FragmentScoreBinding
    internal lateinit var viewModel : ScoreViewModel
    private lateinit var viewModelFactory: ScoreViewModelFactory


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?



    ): View? {


        /**
         * View binding with fragment
         **/

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score,container,false)





        /**
         * variables for Database usage
         */
        val application = requireNotNull(this.activity).application
        val dataSource = userDatabase.getInstance(application).userDao()


        viewModelFactory = ScoreViewModelFactory(dataSource, application)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ScoreViewModel::class.java)
        binding.scoreViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        /**
         * waiting for event from Viewmodel
         */


        viewModel.eventDeleteScores.observe(viewLifecycleOwner, Observer<Boolean> { returned ->
            if (returned) deleteScores()
        })

        viewModel.eventNewScore.observe(viewLifecycleOwner, Observer<List<User>> {
            checkNewScore(it)
        })





        /**
         * Override back button on android Bottom Navigation Bar
         */


        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                /**
                 * navigation to self
                 */
                val action = ScoreFragmentDirections.actionScoreFragmentToTitleFragment()
                NavHostFragment.findNavController(requireParentFragment()).navigate(action)

            }
        })


        /**
         * serttings needed for marquee
         */
        binding.tvPlatz1Name.setSingleLine()
        binding.tvPlatz1Name.isSelected = true

        binding.tvPlatz2Name.setSingleLine()
        binding.tvPlatz2Name.isSelected = true

        binding.tvPlatz3Name.setSingleLine()
        binding.tvPlatz3Name.isSelected = true

        binding.tvPlatz4Name.setSingleLine()
        binding.tvPlatz4Name.isSelected = true



        return binding.root
    }


    /**
     * set new score
     * data comes from database through ScoreViewModel
     */

    private fun checkNewScore(newScore:List<User>) {

        if(newScore.size == 1)
        {
            binding.tvPlatz1Name.text = newScore[0].name

            binding.tvPunkteP1.text = newScore[0].punkte.toString()
        }
        else if(newScore.size == 2)
        {
            binding.tvPlatz1Name.text = newScore[0].name
            binding.tvPlatz2Name.text = newScore[1].name

            binding.tvPunkteP1.text = newScore[0].punkte.toString()
            binding.tvPunkteP2.text = newScore[1].punkte.toString()
        }
        else if (newScore.size == 3)
        {
            binding.tvPlatz1Name.text = newScore[0].name
            binding.tvPlatz2Name.text = newScore[1].name
            binding.tvPlatz3Name.text = newScore[2].name

            binding.tvPunkteP1.text = newScore[0].punkte.toString()
            binding.tvPunkteP2.text = newScore[1].punkte.toString()
            binding.tvPunkteP3.text = newScore[2].punkte.toString()

        }
        else if (newScore.size == 4)
        {
            binding.tvPlatz1Name.text = newScore[0].name
            binding.tvPlatz2Name.text = newScore[1].name
            binding.tvPlatz3Name.text = newScore[2].name
            binding.tvPlatz4Name.text = newScore[3].name

            binding.tvPunkteP1.text = newScore[0].punkte.toString()
            binding.tvPunkteP2.text = newScore[1].punkte.toString()
            binding.tvPunkteP3.text = newScore[2].punkte.toString()
            binding.tvPunkteP4.text = newScore[3].punkte.toString()
        }
        else if (newScore.size > 4)
        {
            binding.tvPlatz1Name.text = newScore[0].name
            binding.tvPlatz2Name.text = newScore[1].name
            binding.tvPlatz3Name.text = newScore[2].name
            binding.tvPlatz4Name.text = newScore[3].name
            binding.tvPlatz5Name.text = newScore[4].name

            binding.tvPunkteP1.text = newScore[0].punkte.toString()
            binding.tvPunkteP2.text = newScore[1].punkte.toString()
            binding.tvPunkteP3.text = newScore[2].punkte.toString()
            binding.tvPunkteP4.text = newScore[3].punkte.toString()
            binding.tvPunkteP5.text = newScore[4].punkte.toString()
        }
    }


    /**
     * navigate to score fragment (to self) --> refresh
     */
    private fun deleteScores()
    {

        val action = ScoreFragmentDirections.actionScoreFragmentSelf()
        NavHostFragment.findNavController(this).navigate(action)
    }


}