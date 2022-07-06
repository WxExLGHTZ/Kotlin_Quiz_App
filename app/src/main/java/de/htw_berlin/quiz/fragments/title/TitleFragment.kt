package de.htw_berlin.quiz.fragments.title
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
import de.htw_berlin.quiz.R
import de.htw_berlin.quiz.databinding.FragmentTitleBinding
import kotlinx.android.synthetic.main.fragment_title.*



class TitleFragment : Fragment() {

    private lateinit var binding: FragmentTitleBinding
    private lateinit var viewModel: TitleViewModel

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {

        /**
         * View binding with fragments
         */

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_title,container,false)
        viewModel = ViewModelProvider(this).get(TitleViewModel::class.java)
        binding.titleViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner


        /**
         * waiting for event from Viewmodel
         */

        viewModel.eventGameStart.observe(viewLifecycleOwner, Observer<Boolean> { hasStarted ->
            if (hasStarted) gameStart()
        })

        viewModel.eventWatchScore.observe(viewLifecycleOwner, Observer<Boolean> { isWatching ->
            if (isWatching) viewScore()
        })


        /**
         * Override back button on android Bottom Navigation Bar
         */
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                /**
                 * navigation to self
                 */
                val action = TitleFragmentDirections.actionTitleFragmentSelf()
                NavHostFragment.findNavController(requireParentFragment()).navigate(action)

            }
        })


        return binding.root
    }


    private fun gameStart() {


        /**
         * EditText input of fragment_title.xml
         */

        if(e_text_name.text.toString().isEmpty())
        {
            Toast.makeText(activity, "Bitte Namen eingeben !", Toast.LENGTH_SHORT).show()
        }
        else
        {

            /**
            navigation to QuestionFragment
             **/

            val action = TitleFragmentDirections.actionTitleFragmentToQuestionFragment(e_text_name.text.toString())
            NavHostFragment.findNavController(this).navigate(action)

        }
    }


    /**
     * Navigation to Score Fragment
     */
    private fun viewScore()
    {
        val action = TitleFragmentDirections.actionTitleFragmentToScoreFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }
}