package com.dicoding.picodiploma.notogo_app.bucket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.notogo_app.bucket.webview.FoodActivity
import com.dicoding.picodiploma.notogo_app.bucket.webview.HotelActivity
import com.dicoding.picodiploma.notogo_app.bucket.webview.TransportActivity
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

        binding.btnLinkTransport.setOnClickListener{
            val intent = Intent(this@DetailGoalActivity, TransportActivity::class.java)
            startActivity(intent)


        }

        binding.btnLinkFood.setOnClickListener{
            val intent = Intent(this@DetailGoalActivity, FoodActivity::class.java)
            startActivity(intent)

        }

        binding.btnLinkHotel.setOnClickListener{
            val intent = Intent(this@DetailGoalActivity, HotelActivity::class.java)
            startActivity(intent)


        }

    }
}