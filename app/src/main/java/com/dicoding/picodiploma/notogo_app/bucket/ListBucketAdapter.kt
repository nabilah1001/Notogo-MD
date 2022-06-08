package com.dicoding.picodiploma.notogo_app.bucket

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.notogo_app.databinding.ItemRowGoalBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemGoals
import com.dicoding.picodiploma.notogo_app.utils.withDateFormat

class ListBucketAdapter(private val result: List<ResultItemGoals>): RecyclerView.Adapter<ListBucketAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowGoalBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(private val binding: ItemRowGoalBinding)  : RecyclerView.ViewHolder(binding.root){
        fun bind(resultItem: ResultItemGoals){
            with(binding){
                titleGoal.text = resultItem.title
                dateGoal.text = resultItem.date?.withDateFormat()

                binding.root.setOnClickListener {
                    Intent(binding.root.context, DetailGoalActivity::class.java)
                }
            }
        }
    }
}