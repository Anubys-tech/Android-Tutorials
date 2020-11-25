package com.anubys.exercises.vocabularytrainerapp.dialog

/** @Author Created by Anubys on the 22.11.2020 */

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider

import com.anubys.exercises.vocabularytrainerapp.R
import com.anubys.exercises.vocabularytrainerapp.main.FirstViewModel
import com.anubys.exercises.vocabularytrainerapp.main.FirstViewModelFactory
import com.anubys.exercises.vocabularytrainerapp.repository.database.Vocabulary

import kotlinx.android.synthetic.main.dialog_input.*


class DialogInput(var vocabulary: Vocabulary? = null): DialogFragment() {
    private val tagDialog = DialogInput::class.java.simpleName

    //private lateinit var rootView: View
    private lateinit var firstViewModel: FirstViewModel


    //* *************************************************** *
    //*                 L I F E - C Y C L E                 *
    //* *************************************************** *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagDialog, "TAG - DialogInput - onCreate()")
        super.onCreate(savedInstanceState)

        setStyle(STYLE_NO_FRAME, R.style.FullScreenDialog)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(tagDialog, "TAG - DialogInput - onCreateView()")

        //rootView = inflater.inflate(R.layout.dialog_input, container, false)
        return (inflater.inflate(R.layout.dialog_input, container, false))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(tagDialog, "TAG - DialogInput - onViewCreated()")
        super.onViewCreated(view, savedInstanceState)

        firstViewModel = ViewModelProvider(requireActivity(), FirstViewModelFactory(requireActivity().application)).get(FirstViewModel::class.java)

        setListener()
        setVocabularyObject()
    }


    //* *************************************************** *
    //*            H E L P E R  -  M E T H O D S            *
    //* *************************************************** *
    private fun setListener() {
        Log.d(tagDialog, "TAG - DialogInput - setListener()")

        //rootView.btn_save.setOnClickListener {  }
        btn_save.setOnClickListener { saveData() }
        btn_abort.setOnClickListener { dismiss() }
    }

    private fun setVocabularyObject() {
        Log.d(tagDialog, "TAG - DialogInput - setVocabularyObject()")

        if (vocabulary != null) {
            etl_foreign.editText?.setText(vocabulary?.foreignWord)
            etl_native.editText?.setText(vocabulary?.nativeWord)
        }
    }

    private fun saveData() {
        Log.d(tagDialog, "TAG - DialogInput - saveData()")

        if (!TextUtils.isEmpty(etl_foreign.editText?.text.toString()) && !TextUtils.isEmpty(etl_native.editText?.text.toString())) {
            if(vocabulary != null) {
                vocabulary?.nativeWord = etl_native.editText?.text.toString()
                vocabulary?.foreignWord = etl_foreign.editText?.text.toString()
                firstViewModel.updateVocabulary(vocabulary!!)
                Toast.makeText(requireContext(),"Voc updated in Database",Toast.LENGTH_SHORT).show()
            } else {
                firstViewModel.insertVocabulary(etl_foreign.editText?.text.toString(),etl_native.editText?.text.toString())
                Toast.makeText(requireContext(),"Voc inserted in Database",Toast.LENGTH_SHORT).show()
            }

            dismiss()
        } else {
            Toast.makeText(requireContext(),"Please insert data in both Fields!", Toast.LENGTH_SHORT).show()
        }
    }
}
