package com.dicoding.picodiploma.notogo_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.dicoding.picodiploma.notogo_app.account.AccountActivity
import com.dicoding.picodiploma.notogo_app.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottom_navbar.background = null
        bottom_navbar.menu.getItem(1).isEnabled = false

 }

    // Option Menu
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_account -> {
                val intent = Intent(this@MainActivity, AccountActivity::class.java)
                startActivity(intent)
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

}