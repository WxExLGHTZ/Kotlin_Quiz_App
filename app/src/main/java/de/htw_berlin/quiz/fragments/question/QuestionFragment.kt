package de.htw_berlin.quiz.fragments.question

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import de.htw_berlin.quiz.databinding.FragmentQuestionBinding
import android.graphics.Color
import android.graphics.Typeface
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_question.view.*
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import de.htw_berlin.quiz.R
import kotlinx.android.synthetic.main.fragment_question.*
import java.util.*
import kotlin.collections.ArrayList


class QuestionFragment : Fragment() {

    internal lateinit var viewModel : QuestionViewModel
    private lateinit var viewModelFactory: QuestionViewModelFactory
    private lateinit var binding: FragmentQuestionBinding





    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {



        /**
         * View binding with fragment
         **/



        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_question, container, false)

        viewModelFactory = QuestionViewModelFactory(QuestionFragmentArgs.fromBundle(requireArguments()).sName)

        viewModel = ViewModelProvider(this, viewModelFactory).get(QuestionViewModel::class.java)

        binding.questionViewModel = viewModel

        binding.lifecycleOwner = viewLifecycleOwner


        /**
         * navigate to title fragment when back button of android bottom bar is pressed
         */

        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {


                Toast.makeText(activity,"Spiel abgebrochen !", Toast.LENGTH_SHORT).show()

                val action = QuestionFragmentDirections.actionQuestionFragmentToTitleFragment()
                NavHostFragment.findNavController(requireParentFragment()).navigate(action)

            }
        })



        viewModel.eventNextQ.observe(viewLifecycleOwner, Observer<Boolean> { nextQ ->
            if (nextQ) nextQfun()
        })

        viewModel.progress.observe(viewLifecycleOwner, Observer<Int> { prog ->
            if (prog > binding.progessBar.progress ) updateProgress(prog)
        })

        viewModel.newQ.observe(viewLifecycleOwner, Observer<String> { nQ ->
             updateQ(nQ)
        })

        viewModel.newA.observe(viewLifecycleOwner, Observer<ArrayList<String>> { nA ->
            updateA(nA)
        })

        viewModel.setDef.observe(viewLifecycleOwner, Observer<Boolean> { default ->
            if(default) defaultOptions()
        })

        viewModel.answerClicked.observe(viewLifecycleOwner, Observer<View> {
            optionClicked(it)
        })

        viewModel.chooseOption.observe(viewLifecycleOwner, Observer<Dictionary<Int,Int>> { dict ->
            answer(dict)
        })

        viewModel.changeButton.observe(viewLifecycleOwner, Observer<String> { s ->
            changeButton(s)
        })

        viewModel.showReult.observe(viewLifecycleOwner, Observer<Dictionary<String,Int>> { resDict ->
            showResult(resDict)
        })


        viewModel.eventEnableOptions.observe(viewLifecycleOwner, Observer<Boolean> { enable ->
            if(enable) enableOption()
        })


        viewModel.eventDisbleOptions.observe(viewLifecycleOwner, Observer<Boolean> { disable ->
            if(disable) disableOption()
        })


        viewModel.eventAnswerNotSelected.observe(viewLifecycleOwner, Observer<Boolean> { ns ->
            if(ns) notSelected()
        })

        return binding.root
    }


    /**
     * setting text of answerButton
     */
    private fun nextQfun()
    {
        binding.buttonAntworten.text = "ANTWORTEN"
    }

    /**
     * update Progresbar in question.xml
     */
    private fun updateProgress(pos : Int)
    {
        binding.progessBar.progess_bar.progress = pos

        binding.tvprog.text = "$pos" + "/" + progess_bar.max

    }

    /**
     * set text of new Question in GUI
     */
    private fun updateQ(nQ : String)
    {
        binding.textViewFrage.text_view_frage.text = nQ

    }


    /**
     * get answer list for current question
     * and set answer options in GUI
     */
    private fun updateA(nA : ArrayList<String>)
    {
        binding.option1.text = nA[0]
        binding.option2.text = nA[1]
        binding.option3.text = nA[2]
        binding.option4.text = nA[3]
    }


    /**
     * default answer options for current game
     */
    private fun defaultOptions()
    {
        val options = ArrayList<TextView>()
        options.add(0,binding.option1)
        options.add(1,binding.option2)
        options.add(2,binding.option3)
        options.add(3,binding.option4)

        /**
         * set color for options
         */

        for (option in options)
        {
            option.setTextColor(Color.parseColor("#808080"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(requireContext(),
                R.drawable.default_options_selection
            )
        }
    }


    /**
     * set color of selected option
     */
    private fun selectedOption(textView: TextView, sONumber: Int)
    {
        defaultOptions()

        textView.setTextColor(Color.parseColor("#5b39c6"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(requireContext(),
            R.drawable.selected_option
        )
    }


    /**
     * set color of a wrong or correct option in GUI
     */

    private fun answer( dict : Dictionary<Int , Int>)
    {
        var key: Int = dict.keys().toList()[0]
        var value: Int = dict.get(key)

        when(key)
        {

            1 -> {
                binding.option1.option_1.background = ContextCompat.getDrawable(requireContext(), value)
            }
            2 -> {
                binding.option2.option_2.background = ContextCompat.getDrawable(requireContext(), value)
            }
            3 -> {
                binding.option3.option_3.background = ContextCompat.getDrawable(requireContext(), value)
            }
            4 -> {
                binding.option4.option_4.background = ContextCompat.getDrawable(requireContext(), value)
            }
        }
    }


    /**
     * check which option is selected
     */
    private fun optionClicked(v: View?) {

        if(v?.tag.toString().toInt() == 1)
        {
            selectedOption(binding.option1, 1)
        }
        else if(v?.tag.toString().toInt() == 2)
        {
            selectedOption(binding.option2, 2)
        }
        else if(v?.tag.toString().toInt() == 3)
        {
            selectedOption(binding.option3, 3)

        }
        else if(v?.tag.toString().toInt() == 4)
        {
            selectedOption(binding.option4, 4)
        }

    }


    /**
     * save name and score and navigate to result fragment
     */
    private fun showResult(dict: Dictionary<String, Int>)
    {
        var resName = dict.keys().toList()[0]
        var reScore = dict.get(resName)

        val action = QuestionFragmentDirections.actionQuestionFragmentToResultFragment(resName,reScore)
        NavHostFragment.findNavController(this).navigate(action)
    }

    /**
     * change text of answer button on GUI
     */
    private fun changeButton(s : String)
    {
        if (s == "ZUM ERGEBNIS")
        {
            binding.buttonAntworten.button_antworten.text = "ZUM ERGEBNIS"
        }
        else if (s == "ANTWORTEN")
        {
            binding.buttonAntworten.button_antworten.text = "ANTWORTEN"
        }
        else if (s == "NÄCHSTE FRAGE")
        {
            binding.buttonAntworten.button_antworten.text = "NÄCHSTE FRAGE"
        }
    }


    /**
     * enabe that the answer options can be chicked
     */
    private fun enableOption()
    {
        binding.option1.isEnabled = true
        binding.option2.isEnabled = true
        binding.option3.isEnabled = true
        binding.option4.isEnabled = true
    }

    /**
     * disabe that the answer options can be chicked
     */

    private fun disableOption()
    {
        binding.option1.isEnabled = false
        binding.option2.isEnabled = false
        binding.option3.isEnabled = false
        binding.option4.isEnabled = false
    }


    /**
     * show toast if anwer option is not selected
     */

    private fun notSelected()
    {
        Toast.makeText(activity, "Bitte Antwort Auswählen !", Toast.LENGTH_SHORT).show()
    }
}
