package com.anubys.exercises.vocabularytrainerapp.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anubys.exercises.vocabularytrainerapp.R


class MainFragment : Fragment() {

    private var rootView: View? = null
    private var recyclerView: RecyclerView? = null
    private var adapter: VocabularyAdapter? = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rootView = view

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = rootView?.findViewById(R.id.rv_vocabulary)
        //Nur zum Testen
        val initList = ArrayList<String>(List(15){""})
        adapter = VocabularyAdapter(initList)
        recyclerView?.adapter = adapter
    }
}