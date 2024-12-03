package com.example.codetutor.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.codetutor.R
import com.example.codetutor.databinding.FragmentHomeBinding
import com.example.codetutor.repository.ChallengeDataSource
import com.example.codetutor.viewmodel.AppViewModel

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel: AppViewModel by activityViewModels()

    private val totalChallenges = ChallengeDataSource.challenges.size

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.btnStartLearning.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_challengesListFragment)
        }

        // Observe completed challenges
        viewModel.completedChallenges.observe(viewLifecycleOwner) { completed ->
            val progress = if (totalChallenges > 0) {
                (completed.size * 100) / totalChallenges
            } else {
                0
            }
            binding.progressBar.progress = progress
            binding.txtProgress.text = "${completed.size} of $totalChallenges Challenges Completed"
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
