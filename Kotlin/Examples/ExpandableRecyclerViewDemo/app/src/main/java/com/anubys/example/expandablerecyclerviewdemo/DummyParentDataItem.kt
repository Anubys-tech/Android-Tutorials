package com.anubys.example.expandablerecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.util.Log

import java.io.Serializable


class DummyParentDataItem(private var itemParent: ItemParent) : Serializable {
    private val tag = DummyParentDataItem::class.java.simpleName


    //* ************************************************ *
    //*          H E L P E R  -  M E T H O D S           *
    //* ************************************************ *
    fun getParentName(): String {
        Log.d(tag,"TAG - DummyParentDataItem - getParentName()")

        return (itemParent.parentName)
    }

    fun setParentName(parentName: String) {
        Log.d(tag,"TAG - DummyParentDataItem - setParentName()")

        itemParent.parentName = parentName
    }

    fun getChildDataItems(): ArrayList<DummyChildDataItem> {
        Log.d(tag,"TAG - RegisterFragment - getChildDataItems()")

        return (itemParent.childDataItems)
    }

    fun setChildDataItems(childDataItems: ArrayList<DummyChildDataItem>) {
        Log.d(tag,"TAG - DummyParentDataItem - setChildDataItems()")

        itemParent.childDataItems = childDataItems
    }
}
