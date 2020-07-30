package com.anubys.example.mvvmdemo.ui.auth

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider

import kotlinx.android.synthetic.main.activity_login.*

import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

import com.anubys.example.mvvmdemo.R
import com.anubys.example.mvvmdemo.databinding.ActivityLoginBinding
import com.anubys.example.mvvmdemo.utils.startHomeActivity

/** @Author Created by Anubys on the 21.07.2020 */


class LoginActivity : AppCompatActivity(), AuthListener, KodeinAware {
    private val tag = LoginActivity::class.java.simpleName

    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()
    private lateinit var viewModel: AuthViewModel


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tag,"TAG - LoginActivity - onCreate()")
        super.onCreate(savedInstanceState)

        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)
        binding.viewmodel = viewModel

        viewModel.authListener = this
    }

    override fun onStart() {
        Log.d(tag,"TAG - LoginActivity - onStart()")
        super.onStart()
        viewModel.user?.let { startHomeActivity() }
    }

    override fun onStarted() {
        Log.d(tag,"TAG - LoginActivity - onStarted()")
        progressbar.visibility = View.VISIBLE
    }

    override fun onSuccess() {
        Log.d(tag,"TAG - LoginActivity - onSuccess()")
        progressbar.visibility = View.GONE
        startHomeActivity()
    }

    override fun onFailure(message: String) {
        Log.d(tag,"TAG - LoginActivity - onFailure()")
        progressbar.visibility = View.GONE
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
