package com.anubys.example.dragdroprecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.layout_drag_drop_item.view.*

import java.util.*


class DragDropRecyclerViewAdapter(private val startDragListener: OnStartDragListener) : RecyclerView.Adapter<DragDropItemViewHolder>(), ItemMoveCallbackListener {
    private val tag = DragDropRecyclerViewAdapter::class.java.simpleName

    private var itemList = emptyList<Item>().toMutableList()


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DragDropItemViewHolder {
        Log.d(tag,"TAG - DragDropRecyclerViewAdapter - onCreateViewHolder()")

        return (DragDropItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_drag_drop_item, parent, false)))
    }

    override fun onBindViewHolder(holder: DragDropItemViewHolder, position: Int) {
        Log.d(tag,"TAG - DragDropRecyclerViewAdapter - onBindViewHolder()")

        holder.bind(itemList[position])
        holder.itemView.imageView.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                this.startDragListener.onStartDrag(holder)
                v.performClick()
            }

            return@setOnTouchListener true
        }
    }

    override fun getItemCount(): Int {
        Log.d(tag,"TAG - DragDropRecyclerViewAdapter - getItemCount()")

        return (itemList.size)
    }

    override fun onRowMoved(fromPosition: Int, toPosition: Int) {
        Log.d(tag,"TAG - DragDropRecyclerViewAdapter - onRowMoved()")

        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(itemList, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(itemList, i, i - 1)
            }
        }

        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onRowSelected(itemViewHolder: DragDropItemViewHolder) {
        Log.d(tag,"TAG - DragDropRecyclerViewAdapter - onRowSelected()")
    }

    override fun onRowClear(itemViewHolder: DragDropItemViewHolder) {
        Log.d(tag,"TAG - DragDropRecyclerViewAdapter - onRowClear()")
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun setItem(newItem: List<Item>) {
        Log.d(tag,"TAG - DragDropRecyclerViewAdapter - setItem()")

        itemList.addAll(newItem)
    }
}
