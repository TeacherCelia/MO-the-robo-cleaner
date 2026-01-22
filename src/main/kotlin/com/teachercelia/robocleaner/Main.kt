package com.teachercelia.robocleaner

import com.teachercelia.robocleaner.application.ExecuteRobotMovementUseCase
import com.teachercelia.robocleaner.infrastructure.RobotInputParser
import java.io.File

fun main(){
    /**
     * This is the Main file! Press PLAY in the right-bottom to start program.
     * You can change the input in the input.txt file, located in the root of
     * this project.
     */

    val input = File("input.txt").readText()

    val parser = RobotInputParser()
    val command = parser.parse(input)

    val useCase = ExecuteRobotMovementUseCase()
    val robots = useCase.executeRobotMovement(command)

    robots.forEach { robot ->
        println("${robot.position.x} ${robot.position.y} ${robot.orientation}")
    }

}