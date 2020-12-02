package com.anubys.exercises.chatbotapp.ui

/** @Author Created by Anubys on the 02.11.2020 */

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import com.anubys.exercises.chatbotapp.R
import com.anubys.exercises.chatbotapp.data.Message
import com.anubys.exercises.chatbotapp.utils.Constants.RECEIVE_ID
import com.anubys.exercises.chatbotapp.utils.Constants.SEND_ID

import kotlinx.android.synthetic.main.layout_message_item.view.*


class MessagingAdapter : RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {
    private val tag = MessagingAdapter::class.java.simpleName

    private var messageList = mutableListOf<Message>()


    //* ************************************************* *
    //*                L I F E - C Y C L E               `*
    //* ************************************************* *
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessagingAdapter.MessageViewHolder {
        Log.d(tag,"TAG - MessageAdapter - onCreateViewHolder()")

        return (MessageViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_message_item, parent, false)))
    }

    override fun onBindViewHolder(holder: MessagingAdapter.MessageViewHolder, position: Int) {
        Log.d(tag,"TAG - MessageAdapter - onBindViewHolder()")

        val currentMessage = messageList[position]
        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tv_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_message.visibility = View.GONE
            }
        }
    }

    override fun getItemCount(): Int {
        Log.d(tag,"TAG - MessageAdapter - getItemCount()")

        return (messageList.size)
    }


    //* ************************************************* *
    //*           H E L P E R - M E T H O D E N           *
    //* ************************************************* *
    fun insertMessage(message: Message) {
        Log.d(tag,"TAG - MessageAdapter - insertMessage()")

        messageList.add(message)
        notifyItemInserted(messageList.size)
    }


    //* ************************************************* *
    //*                     C L A S S                     *
    //* ************************************************* *
    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tag = MessageViewHolder::class.java.simpleName

        init {
            Log.d(tag,"TAG - MessageViewHolder - init()")

            itemView.setOnClickListener {
                Log.d(tag,"TAG - MessageViewHolder - setOnClickListener()")

                //Remove message on the item clicked
                messageList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }
    }
}
