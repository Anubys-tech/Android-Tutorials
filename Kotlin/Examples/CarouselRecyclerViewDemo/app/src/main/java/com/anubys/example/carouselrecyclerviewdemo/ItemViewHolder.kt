package com.anubys.example.carouselrecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log
import android.view.View

import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.list_item.view.*


class ItemViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    private val tag = ItemViewHolder::class.java.simpleName


    //* ************************************************ *
    //*             I T E M  E L E M E N T S             *
    //* ************************************************ *
    fun bind(item: Item) {
        Log.d(tag,"TAG - ItemViewHolder - bind()")

        view.list_item_text.text = item.title
        view.list_item_icon.setImageResource(item.icon)
    }
}
