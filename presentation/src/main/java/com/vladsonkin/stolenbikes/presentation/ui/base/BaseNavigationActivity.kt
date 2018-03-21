package com.vladsonkin.stolenbikes.presentation.ui.base

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.view.MenuItem
import com.vladsonkin.stolenbikes.presentation.R
import com.vladsonkin.stolenbikes.presentation.ui.main.info.InfoActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.search.SearchActivity
import com.vladsonkin.stolenbikes.presentation.ui.main.stolenbikes.StolenBikesActivity

/**
 * Created by Vlad Sonkin
 * on 21 March 2018.
 *
 * Inherit from this activity if you want to use bottom bottom_navigation
 */
abstract class BaseNavigationActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener  {

    private lateinit var navigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation_single_fragment)

        navigationView = findViewById(R.id.navigation)
        navigationView.setOnNavigationItemSelectedListener(this)
    }

    override fun onStart() {
        super.onStart()
        updateNavigationBarState()
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    private fun updateNavigationBarState() {
        selectBottomNavigationBarItem(getNavigationMenuItemId())
    }

    private fun selectBottomNavigationBarItem(itemId: Int) {
        val menuItem = navigationView.menu.findItem(itemId)
        menuItem.isChecked = true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        navigationView.postDelayed({
            when (item.itemId) {
                R.id.navigation_stolen_bikes -> startActivity(Intent(this, StolenBikesActivity::class.java))
                R.id.navigation_search -> startActivity(Intent(this, SearchActivity::class.java))
                R.id.navigation_info -> startActivity(Intent(this, InfoActivity::class.java))
            }
            finish()
        }, 200)

        return true
    }

    abstract fun getNavigationMenuItemId(): Int

}