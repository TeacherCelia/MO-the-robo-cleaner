package com.teachercelia.robocleaner.infrastructure

import com.teachercelia.robocleaner.application.RobotInputCommand
import com.teachercelia.robocleaner.application.RobotProgram
import com.teachercelia.robocleaner.domain.Instruction
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position
import com.teachercelia.robocleaner.domain.Workspace

class RobotInputParser{

    fun parse(input: String): RobotInputCommand {
        // 1 ----
        val lines = input
            .lines()
            .map { it.trim() }

        val workspaceLine = lines.getOrNull(0)
            ?: throw IllegalArgumentException("Empty input")

        // 2 ----
        val workspaceParts = workspaceLine.split(Regex(" "))
        val xMax = workspaceParts[0].toInt()
        val yMax = workspaceParts[1].toInt()
        val workspace = Workspace(xMax, yMax)

        val robotLines = lines.drop(1)
        val pairs = robotLines.chunked(2)
        val programs = mutableListOf<RobotProgram>()

        for (pair in pairs) {
            val positionLine = pair.getOrNull(0) ?: continue
            val instructionLine = pair.getOrNull(1) ?: ""

            // -- parse robotLine: "x y O"
            val parts = positionLine.split(Regex(" "))
            val x = parts[0].toInt()
            val y = parts[1].toInt()
            val orientation = Orientation.valueOf(parts[2])

            /*
            for (c in instructionLine) {
                when (c) {
                    'L' -> robot.turnLeft()
                    'R' -> robot.turnRight()
                    'M' -> robot.move()
                    else -> Unit
                }
            }
             */

            val instructions = instructionLine.map { c ->
                when (c) {
                    'L' -> Instruction.L
                    'R' -> Instruction.R
                    'M' -> Instruction.M
                    else -> throw IllegalArgumentException("Invalid instruction: $c")
                }
            }

            programs.add(
                RobotProgram(
                    start = Position(x, y),
                    orientation = orientation,
                    instructions = instructions
                )
            )

        }
        return RobotInputCommand(
            workspace = workspace,
            programs = programs
        )
    }
}