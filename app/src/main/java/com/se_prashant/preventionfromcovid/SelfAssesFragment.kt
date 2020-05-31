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


class SelfAssesFragment : Fragment() {
    data class Question(val text: String, val answers: List<String>)

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "Are you experiencing any of the following symptoms?",
            answers = listOf("severe difficulty breathing (for example, struggling for each breath, speaking in single words)" ,
                    "severe chest pain" ,
                    "having a very hard time waking up",
                    "lost consciousness","none of the above")
        ),
        Question(
            text = "Are you experiencing any of the following symptoms (or a combination of these symptoms)??",
            answers = listOf("fever","new cough","feeling confused","sore throat","none of the above")
        ),
        Question(
            text = "Are you experiencing any of the following symptoms (or a combination of these symptoms)??",
            answers = listOf("muscle aches","fatigue"," headache","runny nose","none of the above")
        )
    )
    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private val numQuestions = 3
    var flag: Boolean = true
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                                          savedInstanceState: Bundle?): View? {

            // Inflate the layout for this fragment
            val binding = DataBindingUtil.inflate<FragmentSelfAssesBinding>(
                inflater, R.layout.fragment_self_asses, container, false)

            setQuestion()
             binding.assesment  = this

            // Set the onClickListener for the submitButton
            binding.submitButton.setOnClickListener { view: View ->
                val checkedId = binding.questionRadioGroup.checkedRadioButtonId
                // Do nothing if nothing is checked (id == -1)
                if (-1 != checkedId) {
                    var answerIndex = 4
                    when (checkedId) {
                        R.id.firstAnswerRadioButton -> answerIndex = 0
                        R.id.secondAnswerRadioButton -> answerIndex = 1
                        R.id.thirdAnswerRadioButton -> answerIndex = 2
                        R.id.fourthAnswerRadioButton -> answerIndex = 3
                    }
                    // The first answer in the original question is always the correct one, so if our
                    // answer matches, we have the correct answer.
                    if (answers[answerIndex] == currentQuestion.answers[4] && flag==true) {
                        questionIndex++
                      // Advance to the next question
                        if (questionIndex < numQuestions) {
                            currentQuestion = questions[questionIndex]
                            setQuestion()
                            binding.invalidateAll()
                        } else {
                                view.findNavController().navigate(R.id.action_selfAssesFragment_to_greenZoneFragment)
                        }
                    } else {
                        questionIndex++
                        flag = false
                        if (questionIndex < numQuestions) {
                            currentQuestion = questions[questionIndex]
                            setQuestion()
                            binding.invalidateAll()
                        }else
                          view.findNavController().navigate(R.id.action_selfAssesFragment_to_v2selfAssesFragment)
                    }
                }
            }
            return binding.root
        }
    private fun setQuestion() {
            currentQuestion = questions[questionIndex]
            answers = currentQuestion.answers.toMutableList()
        (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.self_assesment_question, questionIndex + 1, numQuestions)
        }
}
