package com.anubys.example.expandablerecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import kotlinx.android.synthetic.main.layout_dummy_parent_item.view.*


class DummyViewHolder(itemView: View, private val dummyParentDataItems: ArrayList<DummyParentDataItem>) : RecyclerView.ViewHolder(itemView) {
    private val tag = DummyViewHolder::class.java.simpleName

    private var context: Context = itemView.context


    //* ************************************************ *
    //*             I T E M  E L E M E N T S             *
    //* ************************************************ *
    fun bind(itemList: DummyParentDataItem) {
        Log.d(tag, "TAG - DragDropItemViewHolder - bind()")

        // Parentelement mit Text füllen und Kinderelemente unsichtbar machen
        itemView.tv_parentName.text = itemList.getParentName()
        itemView.ll_child_items.visibility = View.GONE

        //
        var intMaxNoOfChild = 0
        for (index in 0 until dummyParentDataItems.size) {
            val intMaxSizeTemp: Int = dummyParentDataItems[index].getChildDataItems().size
            if (intMaxSizeTemp > intMaxNoOfChild) intMaxNoOfChild = intMaxSizeTemp
        }

        // Erstellung und einfügen einer TextView in Linearlayout für Kinderelemente
        for (indexView in 0 until intMaxNoOfChild) {
            if (indexView < itemList.getChildDataItems().size) {
                val textView = TextView(context)
                textView.id = indexView
                textView.setPadding(0, 20, 0, 20)
                textView.gravity = Gravity.CENTER
                textView.background = ContextCompat.getDrawable(context, R.drawable.background_sub_module_text)

                textView.text = itemList.getChildDataItems()[indexView].getChildName()

                val layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                textView.setOnClickListener {
                    val textViewClicked = it as TextView
                    Toast.makeText(context, textViewClicked.text.toString(), Toast.LENGTH_SHORT).show()
                }

                itemView.ll_child_items.addView(textView, layoutParams)
            }
        }

        // Auf- und Zuklappen der Elternelemente
        itemView.tv_parentName.setOnClickListener{
            if (itemView.ll_child_items.visibility == View.VISIBLE) {
                itemView.ll_child_items.visibility = View.GONE
            } else {
                itemView.ll_child_items.visibility = View.VISIBLE
            }
        }
    }
}
