package com.anubys.exercises.chatbotapp.ui

/** @Author Created by Anubys on the 02.11.2020 */

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import com.anubys.exercises.chatbotapp.R
import com.anubys.exercises.chatbotapp.data.Message
import com.anubys.exercises.chatbotapp.utils.BotResponse
import com.anubys.exercises.chatbotapp.utils.Constants.OPEN_GOOGLE
import com.anubys.exercises.chatbotapp.utils.Constants.OPEN_SEARCH
import com.anubys.exercises.chatbotapp.utils.Constants.RECEIVE_ID
import com.anubys.exercises.chatbotapp.utils.Constants.SEND_ID
import com.anubys.exercises.chatbotapp.utils.Time

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    //You can ignore this messageList if you're coming from the tutorial, it was used only for my personal debugging
    private var messagesList = mutableListOf<Message>()
    //private var adapter: MessagingAdapter? = null
    private lateinit var adapter: MessagingAdapter
    private val botList = listOf("Peter", "Francesca", "Luigi", "Igor")


    //* ************************************************* *
    //*                L I F E - C Y C L E               `*
    //* ************************************************* *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView()
        clickEvents()

        val random = (0..3).random()
        customBotMessage("Hello! Today you're speaking with ${botList[random]}, how may I help?")
    }

    private fun clickEvents() {
        Log.d(tag,"TAG - MainActivity - clickEvents()")

        //Send a message
        btn_send.setOnClickListener {
            sendMessage()
        }

        //Scroll back to correct position when user clicks on text view
        et_message.setOnClickListener {
            GlobalScope.launch {
                delay(100)
                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)
                }
            }
        }
    }

    private fun recyclerView() {
        Log.d(tag,"TAG - MainActivity - recyclerView()")

        adapter = MessagingAdapter()
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)
    }

    override fun onStart() {
        Log.d(tag,"TAG - MainActivity - onStart()")

        super.onStart()
        //In case there are messages, scroll to bottom when re-opening app
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }

    private fun sendMessage() {
        Log.d(tag,"TAG - MainActivity - sendMessage()")

        val message = et_message.text.toString()
        val timeStamp = Time.timeStamp()

        if (message.isNotEmpty()) {
            //Adds it to our local list
            messagesList.add(Message(message, SEND_ID, timeStamp))
            et_message.setText("")

            adapter.insertMessage(Message(message, SEND_ID, timeStamp))
            rv_messages.scrollToPosition(adapter.itemCount - 1)

            botResponse(message)
        }
    }

    private fun botResponse(message: String) {
        Log.d(tag,"TAG - MainActivity - botResponse()")

        val timeStamp = Time.timeStamp()
        GlobalScope.launch {
            //Fake response delay
            delay(1000)
            withContext(Dispatchers.Main) {
                //Gets the response
                val response = BotResponse.basicResponses(message)

                //Adds it to our local list
                messagesList.add(Message(response, RECEIVE_ID, timeStamp))

                //Inserts our message into the adapter
                adapter.insertMessage(Message(response, RECEIVE_ID, timeStamp))

                //Scrolls us to the position of the latest message
                rv_messages.scrollToPosition(adapter.itemCount - 1)

                //Starts Google
                when (response) {
                    OPEN_GOOGLE -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        site.data = Uri.parse("https://www.google.com/")
                        startActivity(site)
                    }
                    OPEN_SEARCH -> {
                        val site = Intent(Intent.ACTION_VIEW)
                        val searchTerm: String? = message.substringAfterLast("search")
                        site.data = Uri.parse("https://www.google.com/search?&q=$searchTerm")
                        startActivity(site)
                    }
                }
            }
        }
    }

    private fun customBotMessage(message: String) {
        Log.d(tag,"TAG - MainActivity - customBotMessage()")

        GlobalScope.launch {
            delay(1000)
            withContext(Dispatchers.Main) {
                val timeStamp = Time.timeStamp()
                messagesList.add(Message(message, RECEIVE_ID, timeStamp))
                adapter.insertMessage(Message(message, RECEIVE_ID, timeStamp))
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}
