package com.teachercelia.robocleaner.application

import com.teachercelia.robocleaner.domain.Instruction
import com.teachercelia.robocleaner.domain.MoTheRobot
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position
import com.teachercelia.robocleaner.domain.Workspace

class ExecuteRobotMovementUseCase(
){
    fun executeRobotMovement(
        command: RobotInputCommand
    ): List<MoTheRobot>{
        // Steps 3, 4, 5
        val robots = mutableListOf<MoTheRobot>()

        for (program in command.programs) {
            val robot = MoTheRobot(program.start, program.orientation)

            for (i in program.instructions){
                when (i){
                    Instruction.L -> robot.turnLeft()
                    Instruction.R -> robot.turnRight()
                    Instruction.M -> robot.move()
                }
            }

            robots.add(robot)
        }
        return robots

    }

}

data class RobotProgram(
    val start: Position,
    val orientation: Orientation,
    val instructions: List<Instruction>
)

data class RobotInputCommand(
    val workspace: Workspace,
    val programs: List<RobotProgram>
)
