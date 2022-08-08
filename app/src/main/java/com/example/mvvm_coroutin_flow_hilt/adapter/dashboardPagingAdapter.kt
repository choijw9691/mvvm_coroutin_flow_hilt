package com.example.mvvm_coroutin_flow_hilt.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mvvm_coroutin_flow_hilt.R
import com.example.mvvm_coroutin_flow_hilt.databinding.BookItemBinding
import com.example.mvvm_coroutin_flow_hilt.model.ResponseDocument

class dashboardPagingAdapter (

    private val item: (ResponseDocument) -> Unit
) : PagingDataAdapter<ResponseDocument, dashboardPagingAdapter.bookHolder>(comparator) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): bookHolder {
        return bookHolder.create(item, parent)
    }

    override fun onBindViewHolder(holder: bookHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object {
        val comparator = object : DiffUtil.ItemCallback<ResponseDocument>() // 이것은 RecyclerView 목록 어댑터를 정의 할 때 일반적으로 수행하는 것과 동일하게 작동한다.
        {
            override fun areItemsTheSame(oldItem: ResponseDocument, newItem: ResponseDocument): Boolean {
                Log.d("JIWOUNG", "comparator123:"+(oldItem.thumbnail == newItem.thumbnail).toString())
                return oldItem.thumbnail == newItem.thumbnail
            }

            override fun areContentsTheSame(oldItem: ResponseDocument, newItem: ResponseDocument): Boolean {
                Log.d("JIWOUNG","comparator1234: "+(oldItem == newItem).toString())
                return oldItem == newItem

            }
        }
    }

    class bookHolder(
        private val like: (ResponseDocument) -> Unit,
        private val binding: BookItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ResponseDocument?) {
            item?.let { item ->
                Glide.with(binding.root).load(item.thumbnail).centerCrop().into(binding.imageView)

            }
        }

        companion object {
            fun create(
                item: (ResponseDocument) -> Unit,
                parent: ViewGroup
            ): bookHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.book_item, parent, false)
                val binding = BookItemBinding.bind(view)
                return bookHolder(item, binding)
            }
        }
    }
}
