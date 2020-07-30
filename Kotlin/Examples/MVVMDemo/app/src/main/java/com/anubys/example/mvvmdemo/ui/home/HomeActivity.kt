package com.anubys.example.mvvmdemo.ui.home

import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

import com.anubys.example.mvvmdemo.R
import com.anubys.example.mvvmdemo.databinding.ActivityHomeBinding

/** @Author Created by Anubys on the 21.07.2020 */


class HomeActivity : AppCompatActivity(), KodeinAware {
    private val tag = HomeActivity::class.java.simpleName

    private val factory : HomeViewModelFactory by instance()
    private lateinit var viewModel: HomeViewModel


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - HomeActivity - onCreate()")
        super.onCreate(savedInstanceState)

        val binding: ActivityHomeBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        viewModel = ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewmodel = viewModel
    }

    override val kodein by kodein()
}
