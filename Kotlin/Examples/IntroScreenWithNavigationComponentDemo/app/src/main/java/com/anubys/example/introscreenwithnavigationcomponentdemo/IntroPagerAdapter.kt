package com.anubys.example.introscreenwithnavigationcomponentdemo

/** @Author Created by Anubys on the 19.08.2020 */

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class IntroPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle, list: ArrayList<Fragment>) : FragmentStateAdapter(fragmentManager, lifecycle) {
    private val tagFragment = IntroPagerAdapter::class.java.simpleName

    private val fragmentList = list


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun createFragment(position: Int): Fragment {
        Log.d(tagFragment, "TAG - IntroPagerAdapter - createFragment()")
        return (fragmentList[position])
    }
    override fun getItemCount(): Int {
        Log.d(tagFragment, "TAG - IntroPagerAdapter - getItemCount()")
        return (fragmentList.size)
    }
}
