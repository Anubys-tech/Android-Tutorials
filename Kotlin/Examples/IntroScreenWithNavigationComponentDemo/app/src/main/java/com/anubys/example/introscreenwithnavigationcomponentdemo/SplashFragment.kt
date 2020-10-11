package com.anubys.example.introscreenwithnavigationcomponentdemo

/** @Author Created by Anubys on the 19.08.2020 */

import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController


class SplashFragment : Fragment() {
    private val tagFragment = SplashFragment::class.java.simpleName

    private var handler: Handler? = null

    var runnable = Runnable {
        if (introPagerFinish()) {
            findNavController().navigate(R.id.action_splash_to_introPager)
        }
    }


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(tagFragment, "TAG - SplashFragment - onCreateView()")

        handler = Handler(Looper.getMainLooper())

/*        Handler(Looper.getMainLooper()).postDelayed({
            if (introPagerFinish()) {
                findNavController().navigate(R.id.action_splash_to_home)
            } else {
                findNavController().navigate(R.id.action_splash_to_introPager)
            }

        }, 3000)*/

        return (inflater.inflate(R.layout.fragment_splash, container, false))
    }

    override fun onStart() {
        super.onStart()
        handler?.postDelayed(runnable, 3000)
    }

    override fun onStop() {
        super.onStop()
        handler?.removeCallbacks(runnable);
    }

    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun introPagerFinish(): Boolean {
        Log.d(tagFragment, "TAG - SplashFragment - introPagerFinish()")
        val sharedPreferences =  requireActivity().getSharedPreferences(
            "introFinish",
            Context.MODE_PRIVATE
        )
        return (sharedPreferences.getBoolean("Finished", false))
    }
}
