package com.gaspol.expert.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.gaspol.expert.data.source.local.entity.RecentSearchEntity

@Dao
interface RecentSearchDao {
    @Query("SELECT * FROM recent_search")
    fun getAllRecentSearch(): LiveData<List<RecentSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recentSearch: RecentSearchEntity)

    @Delete
    fun delete(recentSearch: RecentSearchEntity)
}