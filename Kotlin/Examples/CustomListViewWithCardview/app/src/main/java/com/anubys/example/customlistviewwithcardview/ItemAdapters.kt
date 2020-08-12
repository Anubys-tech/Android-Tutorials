package com.anubys.example.customlistviewwithcardview

/** @Author Created by Anubys on the 11.08.2020 */

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView


class ItemAdapters constructor(var context: Context, var arrayList: ArrayList<ItemList>) : BaseAdapter() {
    private val tag = ItemAdapters::class.java.simpleName

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        Log.v(tag,"TAG - ItemAdapters - getView()")
        val view = inflater.inflate(R.layout.layout_item_cardview, parent, false)

        val icons: ImageView = view.findViewById(R.id.iv_icon) as ImageView
        val title: TextView = view.findViewById(R.id.tv_title) as TextView
        val detail: TextView = view.findViewById(R.id.tv_detail) as TextView

        val items: ItemList = arrayList[position]

        items.icons?.let { icons.setImageResource(it) }
        title.text = items.title
        detail.text = items.detail

        return (view)
    }

    override fun getItem(position: Int): Any {
        Log.v(tag,"TAG - ItemAdapters - getItem()")
        return (arrayList[position])
    }

    override fun getItemId(position: Int): Long {
        Log.v(tag,"TAG - ItemAdapters - getItemId()")
        return (position.toLong())
    }

    override fun getCount(): Int {
        Log.v(tag,"TAG - ItemAdapters - getCount()")
        return (arrayList.size)
    }



    private class ViewHolder {
        lateinit var title: TextView
        lateinit var detail: TextView
        lateinit var icons: ImageView
    }
}
