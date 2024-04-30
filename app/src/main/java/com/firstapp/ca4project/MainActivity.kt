package com.firstapp.ca4project

import android.os.Bundle
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView

//class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
//
//
//    private lateinit var  drawerLayout:  DrawerLayout
//    private lateinit var navigationView: NavigationView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//
//        // initialize drawerLayout
//        drawerLayout = findViewById(R.id.drawer_layout);
//
//        navigationView = findViewById(R.id.nav_view);
//
//        navigationView.setNavigationItemSelectedListener(this);

//        // initialize the toolbar
//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//
//
//        val navigationView = findViewById<NavigationView>(R.id.nav_view)
//        navigationView.setNavigationItemSelectedListener(this)
//
//        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        if (savedInstanceState == null) {
//            supportFragmentManager.beginTransaction()
//                .replace(R.id.fragment_container, CalculatorFragment())
//                .commit()
//            navigationView.setCheckedItem(R.id.nav_calculator)
//        }
//
//    }
//
//    private  fun replaceFragment(fragment: Fragment) {
//        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
//        transaction.replace(R.id.fragment_container, fragment)
//        transaction.commit()
//    }
//
//    override fun onBackPressed() {
//        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
//            drawerLayout.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
//    }
//
//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//            R.id.nav_calculator -> replaceFragment(CalculatorFragment())
//            R.id.nav_tip -> replaceFragment(TipCalculatorFragment())
//            R.id.nav_note -> replaceFragment(NotesFragment())
//            R.id.nav_about -> replaceFragment(AboutFragment())
//        }
//
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
//}


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize drawerLayout and toolbar
        drawerLayout = findViewById(R.id.drawer_layout)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CalculatorFragment())
                .commit()
            navigationView.setCheckedItem(R.id.nav_calculator)
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.nav_calculator -> {
                replaceFragment(CalculatorFragment())
                toolbar.title = "Calculator"
            }
            R.id.nav_tip -> {
                replaceFragment(TipCalculatorFragment())
                toolbar.title = "Tip Calculator"
            }
            R.id.nav_note -> {
                replaceFragment(NotesFragment())
                toolbar.title = "Notes"
            }
            R.id.nav_about -> {
                replaceFragment(AboutFragment())
                toolbar.title = "About"
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
