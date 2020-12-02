package com.anubys.exercises.chatbotapp.utils

/** @Author Created by Anubys on the 02.11.2020 */

import android.util.Log

import com.anubys.exercises.chatbotapp.utils.Constants.OPEN_GOOGLE
import com.anubys.exercises.chatbotapp.utils.Constants.OPEN_SEARCH


/**
 * DE: Bot gibt eine Antwort auf die gestellt Frage.
 * EN: Bot gives an answer to the question asked.
 */
object BotResponse {
    private val tag = BotResponse::class.java.simpleName


    fun basicResponses(_message: String): String {
        Log.d(tag,"TAG - BotResponse - basicResponses()")

        val random = (0..2).random()
        val message =_message.toLowerCase()

        return when {
            //Flips a coin
            message.contains("flip") && message.contains("coin") -> {
                Log.d(tag,"TAG - BotResponse - flip/coin")

                val r = (0..1).random()
                val result = if (r == 0) "heads" else "tails"

                "I flipped a coin and it landed on $result"
            }
            //Math calculations
            message.contains("solve") -> {
                Log.d(tag,"TAG - BotResponse - solve")

                val equation: String? = message.substringAfterLast("solve")
                return try {
                    val answer = SolveMath.solveMath(equation ?: "0")
                    "$answer"

                } catch (e: Exception) {
                    "Sorry, I can't solve that."
                }
            }
            //Hello
            message.contains("hello") -> {
                Log.d(tag,"TAG - BotResponse - hello")

                when (random) {
                    0 -> "Hello there!"
                    1 -> "Sup"
                    2 -> "Buongiorno!"
                    else -> "error" }
            }
            //How are you?
            message.contains("how are you") -> {
                Log.d(tag,"TAG - BotResponse - how are you")

                when (random) {
                    0 -> "I'm doing fine, thanks!"
                    1 -> "I'm hungry..."
                    2 -> "Pretty good! How about you?"
                    else -> "error"
                }
            }
            //What time is it?
            message.contains("time") && message.contains("?") -> {
                Log.d(tag,"TAG - BotResponse - time")

                Time.timeStamp()
            }
            //Open Google
            message.contains("open") && message.contains("google") -> {
                Log.d(tag,"TAG - BotResponse - open/google")

                OPEN_GOOGLE
            }
            //Search on the internet
            message.contains("search") -> {
                Log.d(tag,"TAG - BotResponse - search")

                OPEN_SEARCH
            }
            //When the programme doesn't understand...
            else -> {
                Log.d(tag,"TAG - BotResponse - don´t understand")

                when (random) {
                    0 -> "I don't understand..."
                    1 -> "Try asking me something different"
                    2 -> "Idk"
                    else -> "error"
                }
            }
        }
    }
}
