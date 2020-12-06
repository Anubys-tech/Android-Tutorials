package com.anubys.example.dragdroprecyclerviewdemo

import com.anubys.example.dragdroprecyclerviewdemo.DragDropItemViewHolder

/** @Author Created by Anubys on the 04.12.2020 */


interface ItemMoveCallbackListener {
    fun onRowMoved(fromPosition: Int, toPosition: Int)
    fun onRowSelected(itemViewHolder: DragDropItemViewHolder)
    fun onRowClear(itemViewHolder: DragDropItemViewHolder)
}
