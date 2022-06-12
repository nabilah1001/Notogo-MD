package com.dicoding.picodiploma.notogo_app.recommend

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.notogo_app.databinding.ItemRecommendBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemRecommendation

class ListRecommendationAdapter(private val result: List<ResultItemRecommendation>): RecyclerView.Adapter<ListRecommendationAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRecommendBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(private val binding: ItemRecommendBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(resultItem: ResultItemRecommendation) {
            with(binding) {
                txtAuthorStory.text = resultItem.image
                Glide.with(binding.root.context)
                    .load(resultItem.image)
                    .into(imgAuthorStory)
                txtAuthorStory.text = resultItem.location

                binding.root.setOnClickListener {
                    val intent = Intent(binding.root.context, DetailTravel::class.java)
                    intent.putExtra("EXTRA_RECOMMEND", resultItem)

                    binding.root.context.startActivity(intent)
                }
            }
        }
    }
}