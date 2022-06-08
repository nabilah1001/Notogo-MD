package com.dicoding.picodiploma.notogo_app.account.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.notogo_app.databinding.ItemRowHistoryBinding
import com.dicoding.picodiploma.notogo_app.model.response.ResultItemHistory

class ListHistoryAdapter(private val result: List<ResultItemHistory>): RecyclerView.Adapter<ListHistoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemRowHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(result[position])
    }

    override fun getItemCount(): Int = result.size


    class ViewHolder(private val binding: ItemRowHistoryBinding)  : RecyclerView.ViewHolder(binding.root){
        fun bind(resultItem: ResultItemHistory){
            with(binding){
                tvHistory.text = resultItem.title
                tvDate.text = resultItem.date

//                binding.root.setOnClickListener {
//                    Intent(binding.root.context, DetailTravel::class.java)
//                }
            }
        }

    }
}