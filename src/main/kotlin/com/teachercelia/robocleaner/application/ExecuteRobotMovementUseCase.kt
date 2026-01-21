package com.teachercelia.robocleaner.application

import com.teachercelia.robocleaner.domain.MoTheRobot
import com.teachercelia.robocleaner.domain.Instruction
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position

class ExecuteRobotMovementUseCase(
){
    fun executeRobotMovement(
        //workspace: Workspace,
        robotInstructions: List<Instruction>
    ): MoTheRobot {
        val robot = MoTheRobot(Position(0,0),Orientation.N)

        for (i in robotInstructions){

            when(i){
                Instruction.L -> robot.turnLeft()
                Instruction.R -> robot.turnRight()
                Instruction.M -> robot.move()
            }
        }
        return robot
    }
}
