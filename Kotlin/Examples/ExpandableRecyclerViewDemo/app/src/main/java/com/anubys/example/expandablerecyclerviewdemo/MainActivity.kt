package com.anubys.example.expandablerecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(){
    private val tag = MainActivity::class.java.simpleName

    private val itemAdapter by lazy {
        DummyDataAdapter()
    }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun initRecyclerView() {
        Log.d(tag, "TAG - MainActivity - initRecyclerView()")

        itemAdapter.setItem(getDummyDataToPass())
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter
        recyclerView.setHasFixedSize(true)
    }

    private fun getDummyDataToPass(): ArrayList<DummyParentDataItem> {
        Log.d(tag, "TAG - MainActivity - getDummyDataToPass()")

        val arrDummyData: ArrayList<DummyParentDataItem> = ArrayList()

        var childDataItems: ArrayList<DummyChildDataItem> = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("A Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("A Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("A Child 3")))
        arrDummyData.add(DummyParentDataItem(ItemParent("A Parent, 3 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("B Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("B Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("B Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("B Child 4")))
        childDataItems.add(DummyChildDataItem(ItemChild("B Child 5")))
        childDataItems.add(DummyChildDataItem(ItemChild("B Child 6")))
        arrDummyData.add(DummyParentDataItem(ItemParent("B Parent, 6 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 4")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 5")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 6")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 7")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 8")))
        childDataItems.add(DummyChildDataItem(ItemChild("C Child 9")))
        arrDummyData.add(DummyParentDataItem(ItemParent("C Parent, 9 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("D Child 1")))
        arrDummyData.add(DummyParentDataItem(ItemParent("D Parent, 1 Children", childDataItems)))

        childDataItems = ArrayList()
        arrDummyData.add(DummyParentDataItem(ItemParent("E Parent, 0 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("F Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("F Child 2")))
        arrDummyData.add(DummyParentDataItem(ItemParent("F Parent, 2 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("G Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("G Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("G Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("G Child 4")))
        arrDummyData.add(DummyParentDataItem(ItemParent("G Parent, 4 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 4")))
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 5")))
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 6")))
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 7")))
        childDataItems.add(DummyChildDataItem(ItemChild("H Child 8")))
        arrDummyData.add(DummyParentDataItem(ItemParent("H Parent, 8 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("I Child 1")))
        arrDummyData.add(DummyParentDataItem(ItemParent("I Parent, 1 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("J Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("J Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("J Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("J Child 4")))
        childDataItems.add(DummyChildDataItem(ItemChild("J Child 5")))
        arrDummyData.add(DummyParentDataItem(ItemParent("J Parent, 5 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("K Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("K Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("K Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("K Child 4")))
        childDataItems.add(DummyChildDataItem(ItemChild("K Child 5")))
        childDataItems.add(DummyChildDataItem(ItemChild("K Child 6")))
        childDataItems.add(DummyChildDataItem(ItemChild("K Child 7")))
        arrDummyData.add(DummyParentDataItem(ItemParent("K Parent, 7 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("L Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("L Child 2")))
        arrDummyData.add(DummyParentDataItem(ItemParent("L Parent, 2 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("M Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("M Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("M Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("M Child 4")))
        childDataItems.add(DummyChildDataItem(ItemChild("M Child 5")))
        arrDummyData.add(DummyParentDataItem(ItemParent("M Parent, 5 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 2")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 3")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 4")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 5")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 6")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 7")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 8")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 9")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 10")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 11")))
        childDataItems.add(DummyChildDataItem(ItemChild("N Child 12")))
        arrDummyData.add(DummyParentDataItem(ItemParent("N Parent, 12 Children", childDataItems)))

        childDataItems = ArrayList()
        childDataItems.add(DummyChildDataItem(ItemChild("O Child 1")))
        childDataItems.add(DummyChildDataItem(ItemChild("O Child 2")))
        arrDummyData.add(DummyParentDataItem(ItemParent("O Parent, 2 Children", childDataItems)))

        return (arrDummyData)
    }
}
