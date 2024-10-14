package com.nkdroid.noteunittesting.other

fun main() {
    println(getReversedString("Hello"))
}

fun getMatrix(row: Int, column: Int): String {
    val strBuilder = StringBuilder()
    for (i in 1..row) {
        for (j in 1..column) {
            strBuilder.append("* ")
        }
        strBuilder.append("\n")
    }
    return strBuilder.toString()
}

fun getPyramid(level: Int): String {
    val strBuilder = StringBuilder()
    for (i in 1..level) {
        for (j in 1..i) {
            strBuilder.append("* ")
        }
        strBuilder.append("\n")
    }
    return strBuilder.toString()
}

fun isPalindrome(str: String): Boolean {
    for (i in 0 until str.length / 2) {
        if (str[i] != str[str.length - 1 - i]) {
            return false
        }
    }
    return true
}

fun getReversedString(str: String): String {
    val strBuilder = StringBuilder()
    for(i in str.length-1 downTo 0) {
        strBuilder.append(str[i])
    }
    return strBuilder.toString()
}