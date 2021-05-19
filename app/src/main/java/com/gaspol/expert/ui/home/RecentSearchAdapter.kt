package com.gaspol.expert.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity
import com.gaspol.expert.databinding.RecentSearchItemBinding
import com.gaspol.expert.helper.RecentSearchDiffCallback

class RecentSearchAdapter : RecyclerView.Adapter<RecentSearchAdapter.RecentSearchViewHolder>() {

    private val recentSearchList = ArrayList<RecentSearchEntity>()

    fun setRecentSearch(recentSearch: List<RecentSearchEntity>){
        val diffCallback = RecentSearchDiffCallback(this.recentSearchList, recentSearch)
        val diffResult = DiffUtil.calculateDiff(diffCallback)

        this.recentSearchList.clear()
        this.recentSearchList.addAll(recentSearch)
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecentSearchViewHolder {
        val recentSearchItemBinding = RecentSearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecentSearchViewHolder(recentSearchItemBinding)
    }

    override fun onBindViewHolder(
        holder: RecentSearchViewHolder,
        position: Int
    ) {
        val recentSearch = recentSearchList[position]
        holder.bind(recentSearch)
        holder.itemView.setOnClickListener{onItemClickCallback.onItemClicked(recentSearchList[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = recentSearchList.size

    class RecentSearchViewHolder(private val binding: RecentSearchItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recentSearch: RecentSearchEntity){
            with(binding){
                tvRecentSearch.text = recentSearch.text
            }
        }
    }

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback : OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: RecentSearchEntity)
    }
}