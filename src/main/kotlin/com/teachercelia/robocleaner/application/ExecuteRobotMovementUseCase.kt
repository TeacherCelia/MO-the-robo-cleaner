package com.teachercelia.robocleaner.application

import com.teachercelia.robocleaner.domain.MoTheRobot
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position

class ExecuteRobotMovementUseCase(
){
    fun executeRobotMovement(
        //workspace: Workspace,
        lines: List<String>
    ): List<MoTheRobot> {
        val robots = mutableListOf<MoTheRobot>()
        var i = 0

        while (i < lines.size) {
            val positionLine = lines[i]
            val instructionLine = lines[i + 1]

            // -- positionLine (robot) --
            val parts = positionLine.split(" ")
            val x = parts[0].toInt()
            val y = parts[1].toInt()
            val orientation = Orientation.valueOf(parts[2])

            val robot = MoTheRobot(Position(x, y), orientation)

            // -- instructionsLine --
            for (c in instructionLine) {
                when (c) {
                    'L' -> robot.turnLeft()
                    'R' -> robot.turnRight()
                    'M' -> robot.move()
                    else -> throw IllegalArgumentException("Invalid instruction, only L, R or M allowed.")
                }
            }

            robots.add(robot)
            i += 2
        }
        return robots
    }

}
