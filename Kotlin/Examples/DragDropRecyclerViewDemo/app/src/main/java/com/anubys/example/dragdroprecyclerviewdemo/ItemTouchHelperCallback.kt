package com.anubys.example.dragdroprecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView


class ItemTouchHelperCallback(var adapter: DragDropRecyclerViewAdapter) : ItemTouchHelper.Callback() {
    private val tag = ItemTouchHelperCallback::class.java.simpleName

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        Log.d(tag,"TAG - ItemTouchHelperCallback - getMovementFlags()")

        return (makeMovementFlags(ItemTouchHelper.UP or ItemTouchHelper.DOWN, 0))
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        Log.d(tag,"TAG - ItemTouchHelperCallback - onMove()")

        adapter.onRowMoved(viewHolder.adapterPosition, target.adapterPosition)
        return (true)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        Log.d(tag,"TAG - ItemTouchHelperCallback - onSelectedChanged()")

        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            if (viewHolder is DragDropItemViewHolder) {
                adapter.onRowSelected(viewHolder)
            }
        }

        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder) {
        Log.d(tag,"TAG - ItemTouchHelperCallback - clearView()")
        super.clearView(recyclerView, viewHolder)

        if (viewHolder is DragDropItemViewHolder) {
            adapter.onRowClear(viewHolder)
        }
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        Log.d(tag,"TAG - ItemTouchHelperCallback - onSwiped()")
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        Log.d(tag,"TAG - ItemTouchHelperCallback - isItemViewSwipeEnabled()")

        return (false)
    }

    override fun isLongPressDragEnabled(): Boolean {
        Log.d(tag,"TAG - ItemTouchHelperCallback - isLongPressDragEnabled()")

        return (false)
    }
}
