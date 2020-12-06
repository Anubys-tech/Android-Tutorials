package com.anubys.example.carouselrecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.os.Bundle
import android.util.Log
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val tag = MainActivity::class.java.simpleName

    private val itemAdapter by lazy {
        CarouselRecyclerViewAdapter{ position: Int, _: Item ->
            Toast.makeText(this, "Pos $position", Toast.LENGTH_LONG).show()
            rv_item_list.smoothScrollToPosition(position)
        }
    }

    private val selectionOptions = listOf(
        Item(getString(R.string.txt_flight), R.drawable.ic_flight),
        Item(getString(R.string.txt_car), R.drawable.ic_car),
        Item(getString(R.string.txt_food), R.drawable.ic_food),
        Item(getString(R.string.txt_gas), R.drawable.ic_gas),
        Item(getString(R.string.txt_home), R.drawable.ic_home)
    )


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView initialisieren
        rv_item_list.initialize(itemAdapter)
        // Übergabe der Elemente (ID), welche die Farbe wechseln sollen
        rv_item_list.setViewsToChangeColor(listOf(R.id.list_item_background, R.id.list_item_text))
        // Übergabe einer Liste mit Items für den Adapter
        itemAdapter.setItems(getOptionsList())
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun getOptionsList(): List<Item> {
        Log.d(tag,"TAG - MainActivity - getOptionsList()")

        val items = mutableListOf<Item>()
        // Wirkürliche Rheinfolge der Auswahlmöglichkeiten im RecyclerView mappen (40 Stück)
        (0..40).map { items.add(selectionOptions.random()) }

        return (items)
    }
}
