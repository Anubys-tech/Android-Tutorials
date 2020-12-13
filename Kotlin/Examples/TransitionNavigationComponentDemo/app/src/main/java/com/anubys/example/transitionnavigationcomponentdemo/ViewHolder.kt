package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView


class ViewHolder (private val view: View, private val listener: ItemClickListener) : RecyclerView.ViewHolder(view) {
    private val tag = ViewHolder::class.java.simpleName

    private val image: ImageView = view.findViewById(R.id.iv_item_image)
    private val name: TextView = view.findViewById(R.id.tv_item_name)


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun bindItem(item: DummyItem) {
        Log.d(tag,"TAG - ViewHolder - bindItem()")

        name.text = item.id
        image.transitionName = item.id

        view.setOnClickListener {
            listener.onItemClickListener(item, image)
        }
    }
}
