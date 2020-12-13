package com.anubys.example.transitionnavigationcomponentdemo

/** @Author Created by Anubys on the 13.12.2020 */


data class DummyItem(val id: String, val content: String, val details: String) {
    private val tag = DummyItem::class.java.simpleName

    override fun toString(): String = content
}
