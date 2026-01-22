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
        /*
           1: divide input into lines
           2: Save first line into workspace variable
           3: Save as much robots as pairs of lines are in the lines input: robot + instructions
           4: Execute movement of first robot, later others...
           5: Give an output
            - Limitations -
            -- First line should be a workspace (2 items) --done!
            -- Next lines should be pairs of robot + instructions --done!
            ----> Robot limitations: it should be inside the workspace
            ----> Instructions limitations: they should be L, R or M only
            --> Extra limitation: each time robot moves, new position has to be inside workspace
         */

        // 1 -----
        val lines = input
            .lines() // divide text each time there is a line jump, in a list
            .map { it.trim() } // delete spaces before and after text

        // 2 -----
        val workspaceLine = lines.getOrNull(0)
            ?: throw IllegalArgumentException("Empty input") // null exception

        val workspaceParts = workspaceLine.split(Regex(" "))
        val xMax = workspaceParts[0].toInt()
        val yMax = workspaceParts[1].toInt()
        val workspace = Workspace(xMax, yMax)

        // 3 -----
        val robots = mutableListOf<MoTheRobot>()
        val robotLines = lines.drop(1) // drop() deletes first element of list

        val pairs = robotLines.chunked(2) // chunks() returns a pair list

        for (pair in pairs) {
            val positionLine = pair.getOrNull(0) ?: continue
            val instructionLine = pair.getOrNull(1) ?: ""

            // -- parse robotLine: "x y O"
            val parts = positionLine.split(Regex(" "))
            val x = parts[0].toInt()
            val y = parts[1].toInt()
            val orientation = Orientation.valueOf(parts[2])

            val robot = MoTheRobot(Position(x, y), orientation)

            // 4 -----
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

        // 5 -----
        return robots

    }

}
