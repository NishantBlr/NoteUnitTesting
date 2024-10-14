package com.nkdroid.noteunittesting.other

fun sum(firstNum: Int, secondNum: Int, sumOpr: (Int, Int) -> Int): Int {
    return sumOpr(firstNum, secondNum)
}

fun multiply(firstNum: Int, secondNum: Int): Int {
    return firstNum * secondNum
}

fun multiply(): (Int, Int) -> Int {
    return ::multiply
}

fun String.returnStringFirstAndLastChar(): String {
    return "${this.first()}${this.last()}"
}

class Abc{
    infix fun square(num: Int): Int {
        return num*num
    }
}

inline fun abc(crossinline def:()->Unit) {
    def()
}

fun nothingFun() {
    println("start nothingFun function")
    abc{
        println("def callback")
        return@abc
    }
    println("end nothingFun function")
}

inline fun <reified T> genericsExample(value: T) {
    println("Type of T: ${T::class.java}")
}

inline fun <reified T> showMessage(marks: Int): T {
    return when(T::class) {
        Int::class -> {
            marks as T
        }
        String::class -> {
            "Marks: $marks" as T
        }
        Abc::class -> {
            Abc() as T
        }
        else -> {
            "Invalid type" as T
        }

    }
}

data class Pen(
    val inkColor: String
) {
    fun showInkColor() {
        println("Ink Color: $inkColor")
    }
}

operator fun Pen.plus(otherPen: Pen): Pen {
    val inkColor = this.inkColor + otherPen.inkColor
    return Pen(inkColor)
}

enum class EnumExample(var msg: String) {
    SUCCESS("Success"),
    ERROR("Error"),
    WARNING("Warning")
}

sealed class SealedClassExample {
    class Success(val msg: String): SealedClassExample()
    class Error(val exception: Exception, msg: String): SealedClassExample()
    class Warning(val msg: String): SealedClassExample()
}

fun main() {
    /**val lam: (Int, Int) -> Int = {a, b -> a + b}
    println(sum(1, 2, lam))

    val multiply = multiply()
    println(multiply(15, 2))

    println("string".returnStringFirstAndLastChar())

    val abc = Abc()
    val result = abc square  2

    println(result)

    nothingFun()*/

    /**genericsExample("Generics")
    genericsExample(100)
    genericsExample(Abc())

    println(showMessage<Int>(70))
    println(showMessage<String>(95))

    val pen1 = Pen(inkColor = "red")
    val pen2 = Pen(inkColor = "blue")
    val pen3 = pen1 + pen2
    pen3.showInkColor()*/

    println(EnumExample.SUCCESS.msg)
    println(EnumExample.SUCCESS.name)
    println(EnumExample.SUCCESS.ordinal)
    EnumExample.SUCCESS.msg = "New Message"
    println(EnumExample.SUCCESS.msg)

    SealedClassExample.Success("Success")
    SealedClassExample.Error(Exception(), "Error")
    SealedClassExample.Warning("Warning")
}