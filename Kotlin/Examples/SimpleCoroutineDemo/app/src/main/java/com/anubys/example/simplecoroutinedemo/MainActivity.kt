package com.anubys.example.simplecoroutinedemo

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

import kotlin.random.Random


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    private var recyclerView: RecyclerView? = null
    private var adapter: RecyclerViewAdapter? = null
    private var layoutManager: LinearLayoutManager? = null
    private var content: ArrayList<Int> = ArrayList()
    private var nContent = 1e6.toLong()
    private val scope = CoroutineScope(Dispatchers.Default)



    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag, "TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
        initRecyclerView()
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.d(tag, "TAG - MainActivity - setListener()")

        btn_delete.setOnClickListener { deleteContent() }
        btn_sort.setOnClickListener { scope.launch {
                sortContent()
            }
        }
        btn_fill.setOnClickListener { scope.launch {
                fillContent()
            }
        }
    }

    private fun initRecyclerView() {
        Log.d(tag, "TAG - MainActivity - initRecyclerView()")

        recyclerView = findViewById(R.id.recyclerview)
        adapter = RecyclerViewAdapter(content)
        layoutManager = LinearLayoutManager(this)
        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        layoutManager?.orientation?.let {
            DividerItemDecoration(recyclerView?.context,
                it
            )
        }?.let { recyclerView?.addItemDecoration(it) }
    }

    private fun deleteContent() {
        Log.d(tag, "TAG - MainActivity - deleteContent()")

        content.clear()
        adapter?.updateContent(content)
    }

    private suspend fun sortContent() {
        Log.d(tag, "TAG - MainActivity - sortContent()")

        content.sortBy { it }
        withContext(Dispatchers.Main) {
            adapter?.updateContent(content)
        }
    }

    private suspend fun fillContent() {
        Log.d(tag, "TAG - MainActivity - fillContent()")

        for (i in 0 until nContent) {
            content.add(Random.nextInt(Int.MIN_VALUE, Int.MAX_VALUE))
        }

        withContext(Dispatchers.Main) {
            adapter?.updateContent(content)
        }
    }
}
