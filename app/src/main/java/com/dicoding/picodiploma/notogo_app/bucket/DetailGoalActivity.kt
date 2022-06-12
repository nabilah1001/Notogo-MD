package com.dicoding.picodiploma.notogo_app.bucket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.notogo_app.R
import com.dicoding.picodiploma.notogo_app.databinding.ActivityAccountBinding
import com.dicoding.picodiploma.notogo_app.databinding.ActivityDetailGoalBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemGoals
import com.dicoding.picodiploma.notogo_app.utils.withDateFormat

class DetailGoalActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailGoalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailGoalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.title = "Detail Goal"

        val dataGoals = intent.getParcelableExtra<ResultItemGoals>("EXTRA_GOALS") as ResultItemGoals

        binding.tvTitleGoal.text = dataGoals.title
        binding.tvNote.text = dataGoals.note
        binding.tvLocation.text = dataGoals.locationName
        binding.tvDate.text = dataGoals.date?.withDateFormat()
        binding.tvBudget.text = dataGoals.budget.toString()
    }

}