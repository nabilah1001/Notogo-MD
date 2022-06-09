package com.dicoding.picodiploma.notogo_app.add.location

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.notogo_app.databinding.ItemRowLocationBinding
import com.dicoding.picodiploma.notogo_app.model.response.ItemLocation

class LocationAdapter : RecyclerView.Adapter<LocationAdapter.UserViewHolder>(){

    private val list = ArrayList<ItemLocation>()
    private var onItemClickCallback : OnItemClickCallback? = null

    fun setOnItemClickCallback (onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setList(users: ArrayList<ItemLocation>){
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(private val binding: ItemRowLocationBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(location: ItemLocation){
            binding.root.setOnClickListener{
                onItemClickCallback?.onItemClicked(location)
            }
            binding.apply {
                tvLocation.text = location.location
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemRowLocationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder((view))
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    interface OnItemClickCallback{
        fun onItemClicked(data: ItemLocation)
    }
}