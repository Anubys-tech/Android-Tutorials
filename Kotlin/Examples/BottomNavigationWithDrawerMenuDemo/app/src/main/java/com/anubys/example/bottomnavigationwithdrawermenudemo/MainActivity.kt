package com.anubys.example.bottomnavigationwithdrawermenudemo

/** @Author Created by Anubys on the 14.08.2020 */

import android.os.Bundle
import android.util.Log
import android.view.Menu
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.ui.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.content_main.*


class MainActivity : AppCompatActivity() {
    private val tagActivity = MainActivity::class.java.simpleName

    private lateinit var appBarConfiguration: AppBarConfiguration


    //* ************************************************ *
    //*               L I F E - C Y C L E                *
    //* ************************************************ *
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(tagActivity, "TAG - MainActivity - onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.nav_settings), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        NavigationUI.setupWithNavController(bottomNavigationView, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        Log.d(tagActivity, "TAG - MainActivity - onCreateOptionsMenu()")
        //menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d(tagActivity, "TAG - MainActivity - onSupportNavigateUp()")
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}
