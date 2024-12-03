package com.example.codetutor.repository

import com.example.codetutor.model.Challenge
import com.example.codetutor.model.Difficulty
import com.example.codetutor.model.TestCase

object ChallengeDataSource {
    val challenges = listOf(
        Challenge(
            id = 1,
            title = "Sum of Two Numbers",
            description = "Write a function that returns the sum of two numbers.",
            difficulty = Difficulty.BEGINNER,
            testCases = listOf(
                TestCase(input = "1,2", expectedOutput = "3"),
                TestCase(input = "5,7", expectedOutput = "12")
            )
        ),
        Challenge(
            id = 2,
            title = "Factorial Calculator",
            description = "Write a function that returns the factorial of a number.",
            difficulty = Difficulty.INTERMEDIATE,
            testCases = listOf(
                TestCase(input = "5", expectedOutput = "120"),
                TestCase(input = "7", expectedOutput = "5040")
            )
        ),

    )
}
