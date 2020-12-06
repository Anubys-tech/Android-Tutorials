package com.anubys.example.dragdroprecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import androidx.recyclerview.widget.RecyclerView


interface OnStartDragListener {
    fun onStartDrag(viewHolder: RecyclerView.ViewHolder)
}
