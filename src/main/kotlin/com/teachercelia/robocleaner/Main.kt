package com.teachercelia.robocleaner

import com.teachercelia.robocleaner.application.ExecuteRobotMovementUseCase
import java.io.File

fun main(){
    // I will be using main for testing!

    // testing input from input.txt
    val input = File("input.txt").readText()

    val useCase = ExecuteRobotMovementUseCase()
    val robots = useCase.executeRobotMovement(input)

    robots.forEach { robot ->
        println("${robot.position.x} ${robot.position.y} ${robot.orientation}")
    }

}