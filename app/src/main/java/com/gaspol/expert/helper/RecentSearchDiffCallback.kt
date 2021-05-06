package com.gaspol.expert.helper

import androidx.recyclerview.widget.DiffUtil
import com.gaspol.expert.data.local.RecentSearchEntity

class RecentSearchDiffCallback(private val mOldRecentSearchList: List<RecentSearchEntity>, private val mNewRecentSearchList: List<RecentSearchEntity>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return mOldRecentSearchList.size
    }

    override fun getNewListSize(): Int {
        return mNewRecentSearchList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldRecentSearchList[oldItemPosition].id == mNewRecentSearchList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldRecentSearch = mOldRecentSearchList[oldItemPosition]
        val newRecentSearch = mNewRecentSearchList[newItemPosition]
        return oldRecentSearch.text == newRecentSearch.text
    }

}