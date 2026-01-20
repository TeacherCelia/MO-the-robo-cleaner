package com.teachercelia.robocleaner.domain

class MoTheRobot(
    val position: Position,
    val orientation: Orientation
)
{

    fun turnRight(robotActualOrientation: Orientation): Orientation{
        return when(robotActualOrientation){
            Orientation.N -> Orientation.E
            Orientation.E -> Orientation.S
            Orientation.S -> Orientation.W
            Orientation.W -> Orientation.N
        }
    }

    fun turnLeft(robotActualOrientation: Orientation): Orientation{
        return when(robotActualOrientation){
            Orientation.N -> Orientation.W
            Orientation.W -> Orientation.S
            Orientation.S -> Orientation.E
            Orientation.E -> Orientation.N
        }
    }

    /*
    * TODO: Rule that Robot position (X,Y) can never be higher than Workspace xMax, yMax or less than 0
    */

    fun move(robotActualOrientation: Orientation, robotActualPosition: Position): Position {
        return when(robotActualOrientation){
            Orientation.N -> Position(robotActualPosition.x,robotActualPosition.y + 1)
            Orientation.E -> Position(robotActualPosition.x + 1, robotActualPosition.y)
            Orientation.S -> Position(robotActualPosition.x, robotActualPosition.y - 1)
            Orientation.W -> Position(robotActualPosition.x - 1, robotActualPosition.y)
        }
    }

}



