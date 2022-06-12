package com.dicoding.picodiploma.notogo_app.account.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.picodiploma.notogo_app.databinding.ItemRowFavoriteBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItem
import com.dicoding.picodiploma.notogo_app.recommend.DetailTravel
import kotlinx.android.synthetic.main.activity_account.*

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
                Glide.with(itemView)
                    .load(resultItem.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .circleCrop()
                    .into(ivFavorite)

                binding.root.setOnClickListener {
                    val intent = Intent(binding.root.context, DetailFavoriteActivity::class.java)
                    intent.putExtra("EXTRA_FAVORITE", resultItem)

                    binding.root.context.startActivity(intent)
                }
            }
        }

    }
}