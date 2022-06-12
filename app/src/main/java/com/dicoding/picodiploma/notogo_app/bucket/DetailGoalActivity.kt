package com.dicoding.picodiploma.notogo_app.bucket

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dicoding.picodiploma.notogo_app.databinding.ActivityDetailGoalBinding

class DetailGoalActivity : AppCompatActivity() {

    private lateinit var detailBinding: ActivityDetailGoalBinding

    companion object {
        const val DETAIL_GOAL = "detail_goal"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailGoalBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)
    }

}