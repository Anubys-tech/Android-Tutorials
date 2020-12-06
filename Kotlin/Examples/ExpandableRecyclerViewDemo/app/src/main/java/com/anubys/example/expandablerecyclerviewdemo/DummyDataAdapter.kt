package com.anubys.example.expandablerecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.layout_dummy_parent_item.view.*


class DummyDataAdapter : RecyclerView.Adapter<DummyViewHolder>() {
    private val tag = DummyDataAdapter::class.java.simpleName

    private var itemList = emptyList<DummyParentDataItem>()


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DummyViewHolder {
        Log.d(tag, "TAG - DummyDataAdapter - onCreateViewHolder()")

        return (DummyViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_dummy_parent_item,
                parent,
                false
            ), itemList as ArrayList<DummyParentDataItem>
        ))
    }

    override fun onBindViewHolder(holder: DummyViewHolder, position: Int) {
        Log.d(tag, "TAG - DummyDataAdapter - onBindViewHolder()")

        // Textnamen in den Parentelement aus der Liste setzen
        val dummyParentDataItem: DummyParentDataItem = itemList[position]
        holder.bind(dummyParentDataItem)

        //
        val noOfChildTextViews: Int = holder.itemView.ll_child_items.childCount
        for (index in 0 until noOfChildTextViews) {
            holder.itemView.ll_child_items.getChildAt(index).visibility = View.VISIBLE
        }

        //
        val noOfChild: Int = dummyParentDataItem.getChildDataItems().size
        if (noOfChild < noOfChildTextViews) {
            for (index in noOfChild until noOfChildTextViews) {
                holder.itemView.ll_child_items.getChildAt(index).visibility = View.GONE
            }
        }

        //
        for (textViewIndex in 0 until noOfChild) {
            holder.itemView.ll_child_items.getChildAt(textViewIndex)
            val currentTextView = holder.itemView.ll_child_items.getChildAt(textViewIndex) as TextView
            currentTextView.text = dummyParentDataItem.getChildDataItems()[textViewIndex].getChildName()
        }
    }

    override fun getItemCount(): Int {
        Log.d(tag, "TAG - DummyDataAdapter - getItemCount()")

        return (itemList.size)
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun setItem(newItemList: List<DummyParentDataItem>) {
        Log.d(tag, "TAG - DummyDataAdapter - setItem()")

        this.itemList = newItemList
    }
}
