package com.anubys.example.mvvmdemo.ui.auth

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import kotlinx.android.synthetic.main.activity_signup.*

import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.kodein

import com.anubys.example.mvvmdemo.R
import com.anubys.example.mvvmdemo.databinding.ActivitySignupBinding
import com.anubys.example.mvvmdemo.ui.home.HomeActivity
import com.anubys.example.mvvmdemo.utils.startHomeActivity

/** @Author Created by Anubys on the 21.07.2020 */


class RegisterActivity : AppCompatActivity(), AuthListener, KodeinAware {
    private val tag = RegisterActivity::class.java.simpleName

    private val factory : AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - RegisterActivity - onCreate()")
        super.onCreate(savedInstanceState)

        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override val kodein by kodein()

    override fun onStarted() {
        Log.d(tag,"TAG - RegisterActivity - onStarted()")
        progressbar.visibility = View.VISIBLE
        Intent(this, HomeActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    override fun onSuccess() {
        Log.d(tag,"TAG - RegisterActivity - onSuccess()")
        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        Log.d(tag,"TAG - RegisterActivity - onFailure()")
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
