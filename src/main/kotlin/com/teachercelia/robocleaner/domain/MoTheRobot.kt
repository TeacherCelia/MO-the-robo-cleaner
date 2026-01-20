package com.teachercelia.robocleaner.domain

class MoTheRobot(
    var position: Position,
    var orientation: Orientation
)
{

    fun turnRight() {
        orientation = when(orientation){
            Orientation.N -> Orientation.E
            Orientation.E -> Orientation.S
            Orientation.S -> Orientation.W
            Orientation.W -> Orientation.N
        }
    }

    fun turnLeft() {
        orientation = when(orientation){
            Orientation.N -> Orientation.W
            Orientation.W -> Orientation.S
            Orientation.S -> Orientation.E
            Orientation.E -> Orientation.N
        }
    }

    /*
    * TODO: Rule that Robot position (X,Y) can never be higher than Workspace xMax, yMax or less than 0
    */

    fun move(): Position {
        position = when(orientation){
            Orientation.N -> Position(position.x,position.y + 1)
            Orientation.E -> Position(position.x + 1, position.y)
            Orientation.S -> Position(position.x, position.y - 1)
            Orientation.W -> Position(position.x - 1, position.y)
        }
        return position
    }

}



