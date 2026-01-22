package com.teachercelia.robocleaner.application

import com.teachercelia.robocleaner.domain.MoTheRobot
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position
import com.teachercelia.robocleaner.domain.Workspace

class ExecuteRobotMovementUseCase(
){
    fun executeRobotMovement(
        input: String
    ): List<MoTheRobot>{
        val lines = input
            .lines()
            .map { it.trim() }

        val workspaceLine = lines.getOrNull(0)
            ?: throw IllegalArgumentException("Empty input")

        val workspaceParts = workspaceLine.split(Regex(" "))
        val xMax = workspaceParts[0].toInt()
        val yMax = workspaceParts[1].toInt()
        val workspace = Workspace(xMax, yMax)

        val robots = mutableListOf<MoTheRobot>()
        val robotLines = lines.drop(1)

        val pairs = robotLines.chunked(2)

        for (pair in pairs) {
            val positionLine = pair.getOrNull(0) ?: continue
            val instructionLine = pair.getOrNull(1) ?: ""

            val parts = positionLine.split(Regex(" "))
            val x = parts[0].toInt()
            val y = parts[1].toInt()
            val orientation = Orientation.valueOf(parts[2])

            val robot = MoTheRobot(Position(x, y), orientation)

            for (c in instructionLine) {
                when (c) {
                    'L' -> robot.turnLeft()
                    'R' -> robot.turnRight()
                    'M' -> robot.move()
                    else -> Unit
                }
            }

            robots.add(robot)
        }

        return robots

    }

}
