package com.example.codetutor.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codetutor.databinding.ItemChallengeBinding
import com.example.codetutor.model.Challenge
import com.example.codetutor.viewmodel.AppViewModel

class ChallengesAdapter(
    private val challenges: List<Challenge>,
    private val viewModel: AppViewModel,
    private val onItemClick: (Challenge) -> Unit,
) : RecyclerView.Adapter<ChallengesAdapter.ChallengeViewHolder>() {

    inner class ChallengeViewHolder(private val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(challenge: Challenge) {
            binding.txtTitle.text = challenge.title
            binding.txtDifficulty.text = challenge.difficulty.name

            // Check if challenge is completed
            val completed = viewModel.completedChallenges.value ?: setOf()
            binding.imgCompleted.visibility =
                if (completed.contains(challenge.id)) View.VISIBLE else View.GONE

            binding.root.setOnClickListener {
                onItemClick(challenge)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        val binding =
            ItemChallengeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ChallengeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        holder.bind(challenges[position])
    }

    override fun getItemCount(): Int = challenges.size
}
