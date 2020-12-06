package com.anubys.example.dragdroprecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), OnStartDragListener {
    private val tag = MainActivity::class.java.simpleName

    private lateinit var itemTouchHelper: ItemTouchHelper

    private val itemAdapter by lazy {
        DragDropRecyclerViewAdapter(this)
    }

    private val listOfSeasons = listOf(
        Item("Januar"),
        Item("Februar"),
        Item("März"),
        Item("April"),
        Item("Mai"),
        Item("Juni"),
        Item("Juli"),
        Item("August"),
        Item("September"),
        Item("Oktober"),
        Item("November"),
        Item("Dezember")
    )


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        initTouchHelper()
    }

    override fun onStartDrag(viewHolder: RecyclerView.ViewHolder) {
        Log.d(tag,"TAG - MainActivity - onStartDrag()")

        itemTouchHelper.startDrag(viewHolder)
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun initAdapter() {
        Log.d(tag,"TAG - MainActivity - initAdapter()")

        itemAdapter.setItem(listOfSeasons)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = itemAdapter
    }

    private fun initTouchHelper() {
        Log.d(tag,"TAG - MainActivity - initTouchHelper()")

        val itemTouchHelperCallback: ItemTouchHelper.Callback = ItemTouchHelperCallback(itemAdapter)
        itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }
}
