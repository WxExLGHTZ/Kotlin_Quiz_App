package de.htw_berlin.quiz.fragments.result

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import de.htw_berlin.quiz.R
import de.htw_berlin.quiz.data.userDatabase
import de.htw_berlin.quiz.databinding.FragmentResultBinding
import de.htw_berlin.quiz.fragments.question.QuestionFragmentDirections

class ResultFragment : Fragment() {

    internal lateinit var binding : FragmentResultBinding
    internal lateinit var viewModel: ResultViewModel
    private lateinit var viewModelFactory: ResultViewModelFactory



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        /**
         * View binding with fragment
         **/


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_result, container, false)

        /**
         * variables for Database usage
         */

        val application = requireNotNull(this.activity).application
        val dataSource = userDatabase.getInstance(application).userDao()

        viewModelFactory = ResultViewModelFactory(ResultFragmentArgs.fromBundle(requireArguments()).sName, ResultFragmentArgs.fromBundle(requireArguments()).score, dataSource , application)

        viewModel = ViewModelProvider(this, viewModelFactory).get(ResultViewModel::class.java)

        binding.resultViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner




        /**
         * waiting for event from Viewmodel
         */

        viewModel.endGame.observe(viewLifecycleOwner, Observer { end ->
            if (end)
            {
                /**
                 * navigate to Title Fragment
                 */
                findNavController().navigate(ResultFragmentDirections.actionResultFragmentToTitleFragment())
            }
        })


        /**
         * navigate to title fragment when back button of android bottom bar is pressed
         */
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                Toast.makeText(activity,"Ergebnis nicht gespeichert !", Toast.LENGTH_SHORT).show()


                /**
                * navigate to title fragmet
                 **/

                val action = ResultFragmentDirections.actionResultFragmentToTitleFragment()
                NavHostFragment.findNavController(requireParentFragment()).navigate(action)

            }
        })

        /**
         * serttings needed for marquee
         */

        binding.textViewName.setSingleLine()
        binding.textViewName.isSelected = true

        return binding.root
    }

}