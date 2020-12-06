package com.anubys.example.expandablerecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log

import java.io.Serializable


class DummyChildDataItem(private var itemChild: ItemChild) : Serializable {
    private val tag = DummyChildDataItem::class.java.simpleName


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    fun getChildName(): String {
        Log.d(tag,"TAG - DummyChildDataItem - DummyChildDataItem()")

        return (itemChild.title)
    }

    fun setChildName(childName: String) {
        Log.d(tag,"TAG - DummyChildDataItem - DummyChildDataItem()")

        itemChild.title = childName
    }
}
