package com.se_prashant.preventionfromcovid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.se_prashant.preventionfromcovid.databinding.FragmentSelfAssesBinding
import com.se_prashant.preventionfromcovid.databinding.FragmentV2selfAssesBinding


class V2selfAssesFragment : Fragment() {

    data class Question(val text: String, val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "Have you travelled outside of India in the last 14 days?",
            answers = listOf("Yes","No")
        ),
        Question(
            text = "Does someone you are in close contact with have COVID-19 (for example, someone in your household or workplace)?",
            answers = listOf("Yes","No")
        ),
        Question(
            text = "Are you in close contact with a person who is sick with respiratory symptoms (for example, fever, cough or difficulty breathing) who recently travelled outside of India?",
            answers = listOf("Yes","No")
        )
    )
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 3
    var flag = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate the layout for this fragment
        val binding = DataBindingUtil.inflate<FragmentV2selfAssesBinding>(
            inflater, R.layout.fragment_v2self_asses, container, false)

        setQuestion()
       binding.assesment2 = this
        // Set the onClickListener for the submitButton
        binding.submitButton.setOnClickListener { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 1
                when (checkedId) {
                    R.id.firstAnswerRadioButton -> answerIndex = 0
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[1] && flag) {
                    questionIndex++
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        view.findNavController().navigate(R.id.action_v2selfAssesFragment2_to_orangeZoneFragment)
                    }
                } else {
                    questionIndex++
                    flag =false
                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    }else
                    view.findNavController()
                        .navigate(R.id.action_v2selfAssesFragment2_to_redZoneFragment)
                }

            }
        }
        return binding.root
    }
     private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        answers = currentQuestion.answers.toMutableList()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.self_assesment_question2, questionIndex + 1, numQuestions)
    }
}
