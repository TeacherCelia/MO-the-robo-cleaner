package com.teachercelia.robocleaner.domain

data class Workspace (
    val xMax: Int,
    val yMax: Int
){
    init {
        require(xMax >= 0) {"X value is less than 0"}
        require(yMax >= 0) {"Y value is less than 0"}
    }
}