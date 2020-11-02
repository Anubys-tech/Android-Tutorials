package com.anubys.exercises.chatbotapp.utils

/** @Author Created by Anubys on the 02.11.2020 */

import android.util.Log


/**
 * DE: Löst die mathematischen Operationen [+, -, *, /] auf und gibt das Ergebnis aus.
 * EN: Solves the mathematical operations [+, -, *, /] and outputs the result.
 */
object SolveMath {
    private val tag = SolveMath::class.java.simpleName

    fun solveMath(equation: String) : Int{
        Log.d(tag,"TAG - SolveMath - solveMath()")

        val newEquation = equation.replace(" ", "")
        Log.d(tag, newEquation)

        return when {
            newEquation.contains("+") -> {
                val split = newEquation.split("+")
                val result = split[0].toInt() + split[1].toInt()
                result
            }
            newEquation.contains("-") -> {
                val split = newEquation.split("-")
                val result = split[0].toInt() - split[1].toInt()
                result
            }
            newEquation.contains("*") -> {
                val split = newEquation.split("*")
                val result = split[0].toInt() * split[1].toInt()
                result
            }
            newEquation.contains("/") -> {
                val split = newEquation.split("/")
                val result = split[0].toInt() / split[1].toInt()
                result
            }
            else -> {
                0
            }
        }
    }
}
