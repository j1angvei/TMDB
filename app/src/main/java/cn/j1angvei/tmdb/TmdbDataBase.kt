package cn.j1angvei.tmdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [PageKey::class, Movie::class], version = 2, exportSchema = false)
@TypeConverters(DbFieldConverters::class)
abstract class TmdbDataBase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    abstract fun pageKeyDao(): PageKeyDao

}
