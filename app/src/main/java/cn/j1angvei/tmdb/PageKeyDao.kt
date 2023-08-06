package cn.j1angvei.tmdb

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PageKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(key: List<PageKey>)

    @Query("SELECT createdAt FROM page_key ORDER BY createdAt DESC LIMIT 1")
    suspend fun getCreateTime(): Long?

    @Query("SELECT * FROM PAGE_KEY WHERE itemId=:id AND type = :type  ")
    suspend fun findByTypeAndId(id: Int, type: PageType): PageKey?

    @Query("DELETE FROM page_key WHERE type = :type")
    suspend fun deleteByType(type: PageType)

    @Query("DELETE FROM page_key")
    suspend fun deleteAll()
}