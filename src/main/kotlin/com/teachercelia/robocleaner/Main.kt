package com.teachercelia.robocleaner

import com.teachercelia.robocleaner.application.ExecuteRobotMovementUseCase
import com.teachercelia.robocleaner.infrastructure.ParseError
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
    val useCase = ExecuteRobotMovementUseCase()

    parser.parse(input).fold(
        ifLeft = { error ->
            val message = when (error) {
                is ParseError.EmptyInput -> error.message
                is ParseError.InvalidWorkspace -> error.message
                is ParseError.InvalidRobot -> error.message
            }
            println("ERROR: $message")
        },
        ifRight = { command ->
            val robots = useCase.executeRobotMovement(command)

            robots.forEach { robot ->
                println("${robot.position.x} ${robot.position.y} ${robot.orientation}")
            }
        }
    )

}