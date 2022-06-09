package com.dicoding.picodiploma.notogo_app.add.location

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.add.AddActivity
import com.dicoding.picodiploma.notogo_app.databinding.ActivityLocationBinding
import com.dicoding.picodiploma.notogo_app.model.response.ItemLocation

class LocationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLocationBinding
    private lateinit var viewModel: LocationViewModel
    private lateinit var adapter: LocationAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLocationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Location"

        adapter = LocationAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : LocationAdapter.OnItemClickCallback {
            override fun onItemClicked(data: ItemLocation) {
                Intent(this@LocationActivity, AddActivity::class.java).also{
                    it.putExtra(AddActivity.EXTRA_LOCATION, data.location)
                    it.putExtra(AddActivity.EXTRA_LOCATION_ID, data.locationId)
                    startActivity(it)
                }
            }

        })

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[LocationViewModel::class.java]

        binding.apply {
            rvLocation.layoutManager = LinearLayoutManager(this@LocationActivity)
            rvLocation.setHasFixedSize(true)
            rvLocation.adapter = adapter
        }

        viewModel.getSearchUsers().observe(this) {
            if (it != null) {
                adapter.setList(it)
                binding.opening.visibility = View.GONE
                binding.tvOpening.visibility = View.GONE
                binding.pbLocation.visibility = View.GONE
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_action, menu)

        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.queryHint = resources.getString(R.string.search_hint)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                searchView.clearFocus()

                binding.pbLocation.visibility = View.VISIBLE
                viewModel.setSearchUsers(query)
                binding.opening.visibility = View.GONE
                binding.tvOpening.visibility = View.GONE

                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.choose_location -> {
                Intent(this, AddActivity::class.java).also{
                    startActivity(it)
                }
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }
}