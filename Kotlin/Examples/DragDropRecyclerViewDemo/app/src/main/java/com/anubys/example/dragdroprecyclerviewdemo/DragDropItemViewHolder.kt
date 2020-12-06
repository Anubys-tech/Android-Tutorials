package com.anubys.example.dragdroprecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log
import android.view.View

import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.layout_drag_drop_item.view.*


class DragDropItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tag = DragDropItemViewHolder::class.java.simpleName


    //* ************************************************ *
    //*             I T E M  E L E M E N T S             *
    //* ************************************************ *
    fun bind(item: Item) {
        Log.d(tag,"TAG - DragDropItemViewHolder - bind()")

        itemView.textView.text = item.title
    }
}
