package com.anubys.example.viewbindingfragmentdemo.ui.slideshow

/** @Author Created by Anubys on the 07.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.Fragment

import com.anubys.example.viewbindingfragmentdemo.databinding.FragmentSlideshowBinding


class SlideshowFragment : Fragment() {
    private val tag2 = SlideshowFragment::class.java.simpleName

    private lateinit var slideshowViewModel: SlideshowViewModel
    private var bindingSlideshow: FragmentSlideshowBinding? = null
    private var i = 0


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.v(tag2,"TAG - SlideshowFragment - onCreateView()")
        bindingSlideshow = FragmentSlideshowBinding.inflate(layoutInflater)
        return (bindingSlideshow?.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.v(tag2,"TAG - SlideshowFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        setListener()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.v(tag2,"TAG - SlideshowFragment - onDestroyView()")
        super.onDestroy()
        bindingSlideshow = null
    }


    //* ************************************************ *
    //*         H E L P E R  -  M E T H O D S            *
    //* ************************************************ *
    private fun setListener() {
        Log.v(tag, "TAG - SlideshowFragment - setListener()")
        bindingSlideshow?.btnSlideshow?.setOnClickListener { inc(i) }
    }

    private fun inc(i: Int) {
        Log.v(tag, "TAG - SlideshowFragment - inc()")
        Toast.makeText(context, "Click image()", Toast.LENGTH_LONG).show()
        bindingSlideshow?.btnSlideshow?.text = i.inc().toString()
    }
}
