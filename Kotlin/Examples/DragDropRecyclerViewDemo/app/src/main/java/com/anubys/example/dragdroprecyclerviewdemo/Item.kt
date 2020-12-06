package com.anubys.example.dragdroprecyclerviewdemo

/** @Author Created by Anubys on the 04.12.2020 */

import androidx.annotation.DrawableRes


data class Item(val title: String, @DrawableRes val icon: Int = R.drawable.ic_launcher_background)
