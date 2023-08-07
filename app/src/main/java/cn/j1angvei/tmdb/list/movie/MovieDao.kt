package cn.j1angvei.tmdb.list.movie

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(list: List<Movie>)

    @Query("SELECT * FROM movie ORDER BY page ASC, idxInPage ASC")
    fun pagingSource(): PagingSource<Int, Movie>

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}