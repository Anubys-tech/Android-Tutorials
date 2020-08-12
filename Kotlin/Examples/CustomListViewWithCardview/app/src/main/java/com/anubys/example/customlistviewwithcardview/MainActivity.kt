package com.anubys.example.customlistviewwithcardview

/** @Author Created by Anubys on the 11.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), AdapterView.OnItemClickListener {
    private val tag = MainActivity::class.java.simpleName

    private var itemAdapters: ItemAdapters? = null
    private var arrayList: ArrayList<ItemList>? = null


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.v(tag,"TAG - MainActivity - onItemClick()")
        val items: ItemList? = arrayList?.get(position)
        Toast.makeText(applicationContext, items?.title, Toast.LENGTH_LONG).show()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun init() {
        Log.v(tag,"TAG - MainActivity - init()")
        arrayList = ArrayList()
        arrayList = setDataItem()
        itemAdapters = ItemAdapters(applicationContext, arrayList!!)
        listview?.adapter = itemAdapters
        listview?.onItemClickListener = this
    }

    private fun setDataItem(): ArrayList<ItemList> {
        Log.v(tag,"TAG - MainActivity - setDataItem()")
        val listItem: ArrayList<ItemList> = ArrayList()
        listItem.add(ItemList(R.drawable.ic_launcher_background, "C Language", "C is a ....."))
        listItem.add(ItemList(R.drawable.ic_launcher_background, "C++ Language", "C++ is a ....."))
        listItem.add(ItemList(R.drawable.ic_launcher_background, "Java Language", "Java is a ....."))
        listItem.add(ItemList(R.drawable.ic_launcher_background, "Kotlin Language", "Kotlin is a ....."))

        return (listItem)
    }

}
