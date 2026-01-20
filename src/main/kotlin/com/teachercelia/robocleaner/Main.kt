package com.teachercelia.robocleaner

import com.teachercelia.robocleaner.domain.MoTheRobot
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position

fun main(){
    // I will be using main for testing!

    // Initiating my first robot:

    val myFirstRobot = MoTheRobot(Position(3,4), Orientation.E)

    println("The first robot is in position ${myFirstRobot.position.x},${myFirstRobot.position.y} and is looking at ${myFirstRobot.orientation}")

}