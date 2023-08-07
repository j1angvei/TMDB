package cn.j1angvei.tmdb.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cn.j1angvei.tmdb.list.PageKey
import cn.j1angvei.tmdb.list.PageKeyDao
import cn.j1angvei.tmdb.list.movie.Movie
import cn.j1angvei.tmdb.list.movie.MovieDao
import cn.j1angvei.tmdb.list.person.Person
import cn.j1angvei.tmdb.list.person.PersonDao

@Database(
    entities = [PageKey::class, Movie::class, Person::class],
    version = 5,
    exportSchema = false
)
@TypeConverters(DbFieldConverters::class)
abstract class TmdbDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun personDao(): PersonDao

    abstract fun pageKeyDao(): PageKeyDao

}
