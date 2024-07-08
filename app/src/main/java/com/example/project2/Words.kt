package com.example.project2

import java.io.Serializable

data class Words (
    val source: String,
    val definition: String,
    val examples: String,
    val random: String,
    val syllable: String,
    val view5: String,
    val view6: String,
    val view7: String,
    val view8: String,
    val view9: String,
    val view10: String,
    val view11: String,
    val view12: String,
    val view13: String,
    val view14: String,
    val view15: String,
    val view16: String,
    val view17: String,
    val view18: String,
    val view19: String,
    val view20: String,
    val view21: String,
    val view22: String,
    val view23: String

    ) : Serializable {
    constructor() : this("","","", "","", "","","",",", ",",
    "", "", "", "", "", "","", "","","","", "", "," , "")
}

