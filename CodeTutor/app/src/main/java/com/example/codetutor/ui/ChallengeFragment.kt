package com.example.codetutor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.codetutor.databinding.FragmentChallengeBinding
import com.example.codetutor.model.Challenge
import com.example.codetutor.repository.ChallengeDataSource
import com.example.codetutor.viewmodel.AppViewModel

class ChallengeFragment : Fragment() {

    private var _binding: FragmentChallengeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppViewModel by activityViewModels()
    private val args: ChallengeFragmentArgs by navArgs()

    private lateinit var challenge: Challenge

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChallengeBinding.inflate(inflater, container, false)

        challenge = ChallengeDataSource.challenges.first { it.id == args.challengeId }

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        binding.txtTitle.text = challenge.title
        binding.txtDescription.text = challenge.description

        binding.btnSubmit.setOnClickListener {
            val userCode = binding.editTextCode.text.toString()
            val result = evaluateCode(userCode)
            binding.txtResult.text = result
            if (result.contains("All test cases passed")) {
                viewModel.markChallengeCompleted(challenge.id)
            }
        }
    }

    private fun evaluateCode(userCode: String): String {
        // Simplified evaluation logic for demonstration purposes
        // In a real app, you'd send code to a server or use a code execution API
        // For now, we'll simulate correct output if the userCode contains the word "return"
        return if (userCode.contains("return")) {
            "All test cases passed!"
        } else {
            "Some test cases failed. Try again."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
