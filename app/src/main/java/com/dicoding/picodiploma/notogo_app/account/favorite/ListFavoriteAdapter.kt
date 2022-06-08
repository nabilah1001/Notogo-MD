package com.dicoding.picodiploma.notogo_app.account.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.notogo_app.databinding.ItemRowFavoriteBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItem
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemGoals
import com.dicoding.picodiploma.notogo_app.recommend.DetailTravel

class ListFavoriteAdapter(private val result: List<ResultItem>): RecyclerView.Adapter<ListFavoriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(private val binding: ItemRowFavoriteBinding)  : RecyclerView.ViewHolder(binding.root){
        fun bind(resultItem: ResultItem){
            with(binding){
                tvLocation.text = resultItem.location

                binding.root.setOnClickListener {
                    Intent(binding.root.context, DetailTravel::class.java)
                }
            }
        }

    }
}