package com.teachercelia.robocleaner

import com.teachercelia.robocleaner.domain.MoTheRobot
import com.teachercelia.robocleaner.domain.Orientation
import com.teachercelia.robocleaner.domain.Position
import com.teachercelia.robocleaner.domain.Workspace

fun main(){
    // I will be using main for testing!

    // Initiating my first robot:

    val myFirstRobot = MoTheRobot(Position(3,4), Orientation.E)

    println("The first robot is in position ${myFirstRobot.position.x},${myFirstRobot.position.y} and is looking at ${myFirstRobot.orientation}")

    /* This throws an exception due to the Workspace 'init', I will decide if I keep this validation this way or not...
    val myWorkspace = Workspace(-2, 4)
    println(myWorkspace)
     */
}