package com.example.project2

import java.io.Serializable
data class newWord(
    val task: String,
    val task1: String,
    val task2: String,
    val task3: String
) : Serializable {
    constructor() : this("", "", "", "")
}
