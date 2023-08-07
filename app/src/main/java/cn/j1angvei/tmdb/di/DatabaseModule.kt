package cn.j1angvei.tmdb.di

import android.content.Context
import androidx.room.Room
import cn.j1angvei.tmdb.db.TmdbDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    private const val DB_NAME = "tmdb.db"

    @Provides
    @Singleton
    fun provideTmdbDatabase(@ApplicationContext context: Context): TmdbDataBase {
        return Room.databaseBuilder(context, TmdbDataBase::class.java, DB_NAME)
            .fallbackToDestructiveMigrationOnDowngrade()
            .fallbackToDestructiveMigration().build()
    }
}