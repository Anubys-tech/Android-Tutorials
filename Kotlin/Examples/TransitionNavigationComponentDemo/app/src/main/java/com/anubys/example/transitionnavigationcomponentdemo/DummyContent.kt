package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.util.Log

import java.util.HashMap


object DummyContent {
    private val tag = DummyContent::class.java.simpleName

    val items: MutableList<DummyItem> = ArrayList()
    private val itemsMap: MutableMap<String, DummyItem> = HashMap()
    private const val counter = 25


    //* ************************************************ *
    //*                   I N I T                        *
    //* ************************************************ *
    init {
        Log.d(tag,"TAG - DummyContent - init()")

        for (i in 1..counter) {
            addItem(createDummyItem(i))
        }
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun addItem(item: DummyItem) {
        Log.d(tag,"TAG - DummyContent - addItem()")

        items.add(item)
        itemsMap[item.id] = item
    }

    private fun createDummyItem(position: Int): DummyItem {
        Log.d(tag,"TAG - DummyContent - createDummyItem()")

        return (DummyItem(position.toString(), "Item $position", makeDetails(position)))
    }

    private fun makeDetails(position: Int): String {
        Log.d(tag,"TAG - DummyContent - makeDetails()")

        val builder = StringBuilder()
        builder.append("Angaben zum Artikel: ").append(position)

        for (i in 0 until position) {
            builder.append("\nWeitere Informationen finden Sie hier")
        }

        return (builder.toString())
    }
}
