package cn.j1angvei.tmdb.popular.person

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(people: List<Person>)

    @Query("SELECT * FROM person ORDER BY page ASC, idxInPage ASC")
    fun pagingSource(): PagingSource<Int, Person>

    @Query("DELETE FROM movie")
    suspend fun deleteAll()
}