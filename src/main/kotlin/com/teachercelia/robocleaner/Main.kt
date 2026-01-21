package com.teachercelia.robocleaner

import com.teachercelia.robocleaner.application.ExecuteRobotMovementUseCase
import com.teachercelia.robocleaner.domain.Instruction

fun main(){
    // I will be using main for testing!

    // Test of movement without laws (it is free!)
    println("Testing input")
    val input = listOf(Instruction.R, Instruction.M, Instruction.M)
    val useCase = ExecuteRobotMovementUseCase()
    val robot = useCase.executeRobotMovement(input)

    println("Final robot location: position=${robot.position} orientation=${robot.orientation}")

}