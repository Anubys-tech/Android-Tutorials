package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */

import android.widget.ImageView


interface ItemClickListener {
    fun onItemClickListener(item: DummyItem, imageView: ImageView)
}
