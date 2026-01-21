package com.teachercelia.robocleaner

import com.teachercelia.robocleaner.application.ExecuteRobotMovementUseCase
import com.teachercelia.robocleaner.domain.Instruction

fun main(){
    // I will be using main for testing!

    // starting with the sample input without limits as a list of strings
    val testOfInstructions = listOf(
        "1 2 N",
        "LMLMLMLMM",
        "3 3 E",
        "MMRMMRMRRM",
        "2 3 S",
        "MMRRMMLM"
    )

    // call the usecase
    val useCase = ExecuteRobotMovementUseCase()
    val robots = useCase.executeRobotMovement(testOfInstructions)

    // print each robot
    robots.forEach {
        println("${it.position.x} ${it.position.y} ${it.orientation}")
    }

}