package com.dicoding.picodiploma.notogo_app.account.history

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.notogo_app.databinding.ActivityDetailHistoryBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemHistory
import com.dicoding.picodiploma.notogo_app.utils.withDateFormat

class DetailHistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Detail History"

        val dataGoals: ResultItemHistory? = intent.getParcelableExtra("EXTRA_HISTORY")

        if (dataGoals != null) {
            binding.tvTitleGoal.text = dataGoals.title
            binding.tvNote.text = dataGoals.note
            binding.tvLocation.text = dataGoals.locationName
            binding.tvDate.text = dataGoals.date?.withDateFormat()
            binding.tvBudget.text = dataGoals.budget.toString()
        }
    }
}