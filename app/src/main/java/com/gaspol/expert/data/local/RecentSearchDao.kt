package com.gaspol.expert.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface RecentSearchDao {
    @Query("SELECT * FROM recent_search")
    fun getAllRecentSearch(): LiveData<List<RecentSearchEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(recentSearch: RecentSearchEntity)

    @Delete
    fun delete(recentSearch: RecentSearchEntity)
}