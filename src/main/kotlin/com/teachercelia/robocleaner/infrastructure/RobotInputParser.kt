package com.teachercelia.robocleaner.infrastructure

import arrow.core.Either
import arrow.core.raise.either
import arrow.core.raise.ensure
import com.teachercelia.robocleaner.application.RobotInputCommand
import com.teachercelia.robocleaner.application.RobotProgram
import com.teachercelia.robocleaner.domain.Instruction
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position
import com.teachercelia.robocleaner.domain.Workspace

class RobotInputParser{

    fun parse(input: String): Either<ParseError, RobotInputCommand> = either {

        // Parse lines
        val lines = input
            .lines()
            .map { it.trim() }

        ensure(lines.isNotEmpty()) {
            ParseError.EmptyInput("Empty input")
        }

        // Parse workspace
        val workspaceLine = lines[0]
        val workspaceParts = workspaceLine.split(Regex(" "))

        ensure(workspaceParts.size == 2) {
            ParseError.InvalidWorkspace(
                "Invalid workspace line. Expected: 'xMax yMax' but got: '$workspaceLine'"
            )
        }

        val xMax = workspaceParts[0].toIntOrNull() ?: raise(ParseError.InvalidWorkspace("Invalid workspace line."))
        val yMax = workspaceParts[1].toIntOrNull() ?: raise(ParseError.InvalidWorkspace("Invalid workspace line."))
        val workspace = Workspace(xMax, yMax)

        // validate robot pairs
        val robotLines = lines.drop(1)

        ensure(robotLines.size % 2 == 0) {
            ParseError.InvalidRobot(
                "Invalid input: expected pairs of lines, but got ${robotLines.size} lines after workspace."
            )
        }

        val pairs = robotLines.chunked(2)

        // parse robot programs
        val programs = mutableListOf<RobotProgram>()

        for (pair in pairs) {
            val positionLine = pair.getOrNull(0) ?: raise(ParseError.InvalidRobot("Invalid robots input"))
            val instructionLine = pair.getOrNull(1) ?: raise(ParseError.InvalidRobot("Invalid robots input"))

            val parts = positionLine.split(Regex(" "))
            ensure(parts.size >= 3) { ParseError.InvalidRobot("Invalid robots input") }

            val x = parts[0].toInt()
            val y = parts[1].toInt()
            val orientation = Orientation.valueOf(parts[2])

            val instructions = instructionLine.map { c ->
                when (c) {
                    'L' -> Instruction.L
                    'R' -> Instruction.R
                    'M' -> Instruction.M
                    else -> raise(ParseError.InvalidRobot("Invalid robots input"))
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
        RobotInputCommand(
            workspace = workspace,
            programs = programs
        )
    }
}

sealed class ParseError {
    data class EmptyInput(val message: String) : ParseError()
    data class InvalidWorkspace(val message: String) : ParseError()
    data class InvalidRobot(val message: String) : ParseError()
}