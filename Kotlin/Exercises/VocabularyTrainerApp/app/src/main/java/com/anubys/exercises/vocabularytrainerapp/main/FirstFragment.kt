package com.anubys.exercises.vocabularytrainerapp.main

/** @Author Created by Anubys on the 22.11.2020 */

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView

import com.anubys.exercises.vocabularytrainerapp.R
import com.anubys.exercises.vocabularytrainerapp.dialog.DialogInput
import com.anubys.exercises.vocabularytrainerapp.interfaces.OnItemClickListener
import com.anubys.exercises.vocabularytrainerapp.interfaces.OnItemLongClickListener
import com.anubys.exercises.vocabularytrainerapp.repository.database.Vocabulary


class FirstFragment : Fragment() {
    private val tagFragment = FirstFragment::class.java.simpleName

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: VocabularyAdapter
    private lateinit var firstViewModel: FirstViewModel


    //* *************************************************** *
    //*                 L I F E - C Y C L E                 *
    //* *************************************************** *
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) : View? {
        Log.d(tagFragment, "TAG - FirstFragment - onCreateView()")

        return (inflater.inflate(R.layout.fragment_first, container, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tagFragment, "TAG - FirstFragment - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        firstViewModel = ViewModelProvider(requireActivity(), FirstViewModelFactory(requireActivity().application)).get(FirstViewModel::class.java)
        rootView = view

        initRecyclerView()
        initObserver()
    }


    //* *************************************************** *
    //*            H E L P E R  -  M E T H O D S            *
    //* *************************************************** *
    private fun initRecyclerView() {
        Log.d(tagFragment, "TAG - FirstFragment - initRecyclerView()")

        recyclerView = rootView.findViewById(R.id.rv_vocabulary)
        adapter = VocabularyAdapter(ArrayList())
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object: OnItemClickListener{
            override fun setOnItemClickListener(position: Int) {
                Log.d(tagFragment, "TAG - FirstFragment - setOnItemClickListener()")

                val dialog = DialogInput(adapter.list[position])
                dialog.show(parentFragmentManager, "Update Vocabulary")
            }
        })

        adapter.setOnItemLongClickListener(object: OnItemLongClickListener {
            override fun setOnItemLongClickListener(position: Int) {
                Log.d(tagFragment, "TAG - FirstFragment - setOnItemLongClickListener()")
                startAlarmDialog(adapter.list[position])
            }
        })
    }

    private fun initObserver() {
        Log.d(tagFragment, "TAG - FirstFragment - initObserver()")

        firstViewModel.getLiveVocabularyList().observe(viewLifecycleOwner, { items ->
            adapter.updateContent(ArrayList(items))
        })
    }

    private fun startAlarmDialog(vocabulary: Vocabulary) {
        Log.d(tagFragment, "TAG - FirstFragment - startAlarmDialog()")

        val builder: AlertDialog.Builder = AlertDialog.Builder(requireContext())
        builder.apply {
            setMessage("Warning - Entry will be deleted")
            setPositiveButton("Ok") { _, _ ->
                Toast.makeText(requireContext(), "${vocabulary.foreignWord} deleted", Toast.LENGTH_SHORT).show()
                firstViewModel.deleteVocabulary(vocabulary)
            }
            setNegativeButton("Abort") { dialog, _ ->
                dialog.dismiss()
            }
        }

        val dialog = builder.create()
        dialog.show()
    }
}
