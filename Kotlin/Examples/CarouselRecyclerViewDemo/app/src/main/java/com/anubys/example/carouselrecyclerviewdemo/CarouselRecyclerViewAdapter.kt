package com.anubys.example.carouselrecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView


class CarouselRecyclerViewAdapter(val itemClick: (position: Int, item: Item) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {
    private val tag = CarouselRecyclerViewAdapter::class.java.simpleName

    private var itemList: List<Item> = listOf()

    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        Log.d(tag,"TAG - ItemAdapter - onCreateViewHolder()")

        return (ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        Log.d(tag,"TAG - ItemAdapter - onBindViewHolder()")

        holder.bind(itemList[position])
        // Setzten eines Klick-Ereignises
        holder.itemView.setOnClickListener {
            itemClick(position, itemList[position])
        }
    }

    override fun getItemCount(): Int {
        Log.d(tag,"TAG - ItemAdapter - getItemCount()")

        return (itemList.size)
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun setItems(newItems: List<Item>) {
        Log.d(tag,"TAG - ItemAdapter - setItems()")

        itemList = newItems
        notifyDataSetChanged()
    }
}
