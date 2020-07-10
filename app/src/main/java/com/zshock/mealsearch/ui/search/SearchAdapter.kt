package com.zshock.mealsearch.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zshock.mealsearch.R
import com.zshock.mealsearch.databinding.SearchItemBinding
import com.zshock.mealsearch.domain.model.Meal

class SearchAdapter(private var callback: Callback) :
    ListAdapter<Meal, SearchAdapter.ViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.search_item, parent, false
            ), callback
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: SearchItemBinding, callback: Callback) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener { callback.onMealSelected(binding.viewModel!!.meal) }
        }

        fun bind(item: Meal) {
            with(binding) {
                viewModel = SearchItemViewModel(item)
                executePendingBindings()
            }
        }
    }

    interface Callback {
        fun onMealSelected(meal: Meal)
    }

    class DiffCallback : DiffUtil.ItemCallback<Meal>() {
        override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean {
            return oldItem.title == newItem.title
        }

    }
}
