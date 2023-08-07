package cn.j1angvei.tmdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import cn.j1angvei.tmdb.popular.person.Person
import cn.j1angvei.tmdb.popular.person.PersonDao

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
