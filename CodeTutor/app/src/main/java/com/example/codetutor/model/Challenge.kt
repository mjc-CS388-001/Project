package com.example.codetutor.model

data class Challenge(
    val id: Int,
    val title: String,
    val description: String,
    val difficulty: Difficulty,
    val testCases: List<TestCase>
)

enum class Difficulty {
    BEGINNER,
    INTERMEDIATE,
    ADVANCED
}

data class TestCase(
    val input: String,
    val expectedOutput: String
)
