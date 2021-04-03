package com.akih.materiweek2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.akih.materiweek2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var toogle : ActionBarDrawerToggle
    val mFragmentManager = supportFragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val home = HomeFragment()
        val playlist = PlaylistFragment()
        val favorite = FavoriteFragment()
        setContentView(binding.root)
        toogle = ActionBarDrawerToggle(this, binding.Drawerlayout, R.string.open, R.string.close)
        binding.Drawerlayout.addDrawerListener(toogle)
        toogle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navDrawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> movefrag(home)
                R.id.nav_playlist -> movefrag(playlist)
                R.id.nav_fav -> movefrag(favorite)
                R.id.nav_logout -> Toast.makeText(applicationContext, "you click item Logout", Toast.LENGTH_SHORT).show()
            }
            true
        }
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> movefrag(home)
                R.id.nav_playlist -> movefrag(playlist)
                R.id.nav_fav -> movefrag(favorite)
                R.id.nav_logout -> Toast.makeText(applicationContext, "you click item Logout", Toast.LENGTH_SHORT).show()
            }
            true
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toogle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun movefrag(fragment: Fragment){
        mFragmentManager.beginTransaction()
                .replace(R.id.container_frame, fragment)
                .addToBackStack(null)
                .commit()
        Toast.makeText(applicationContext, "you click a item", Toast.LENGTH_SHORT).show()
        binding.Drawerlayout.closeDrawer(GravityCompat.START)
    }
}