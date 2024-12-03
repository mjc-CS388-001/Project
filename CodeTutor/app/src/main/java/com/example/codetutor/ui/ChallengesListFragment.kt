package com.example.codetutor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.codetutor.adapter.ChallengesAdapter
import com.example.codetutor.databinding.FragmentChallengesListBinding
import com.example.codetutor.model.Challenge
import com.example.codetutor.repository.ChallengeDataSource
import com.example.codetutor.viewmodel.AppViewModel

class ChallengesListFragment : Fragment() {

    private var _binding: FragmentChallengesListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppViewModel by activityViewModels()

    private lateinit var adapter: ChallengesAdapter
    private lateinit var challenges: List<Challenge>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChallengesListBinding.inflate(inflater, container, false)

        challenges = ChallengeDataSource.challenges

        setupRecyclerView()

        return binding.root
    }

    private fun setupRecyclerView() {
        adapter = ChallengesAdapter(challenges, viewModel) { challenge ->
            if (isChallengeUnlocked(challenge)) {
                val action =
                    ChallengesListFragmentDirections.actionChallengesListFragmentToChallengeFragment(
                        challengeId = challenge.id
                    )
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(),
                    "Complete previous challenges to unlock this one.",
                    Toast.LENGTH_SHORT).show()
            }
        }
        binding.recyclerViewChallenges.adapter = adapter
    }

    private fun isChallengeUnlocked(challenge: Challenge): Boolean {
        val completed = viewModel.completedChallenges.value ?: setOf()
        return when (challenge.difficulty) {
            com.example.codetutor.model.Difficulty.BEGINNER -> true
            com.example.codetutor.model.Difficulty.INTERMEDIATE -> {
                completedAllDifficulty(com.example.codetutor.model.Difficulty.BEGINNER)
            }
            com.example.codetutor.model.Difficulty.ADVANCED -> {
                completedAllDifficulty(com.example.codetutor.model.Difficulty.INTERMEDIATE)
            }
        }
    }

    private fun completedAllDifficulty(difficulty: com.example.codetutor.model.Difficulty): Boolean {
        val completed = viewModel.completedChallenges.value ?: setOf()
        val challengesOfDifficulty = challenges.filter { it.difficulty == difficulty }
        return challengesOfDifficulty.all { completed.contains(it.id) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
