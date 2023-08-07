package cn.j1angvei.tmdb.popular.person

import androidx.room.Entity
import androidx.room.PrimaryKey
import cn.j1angvei.tmdb.TMDB_IMG_URL

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
@Entity("person")
data class Person(
    @PrimaryKey val id: Int,
    val name: String,
    val profilePath: String?,
    val gender: Int,
    val backdropPath: String?,
    val popularity: Float,
    var page: Int,
    var idxInPage: Int
) {
    val fullProfile: String
        get() = TMDB_IMG_URL + profilePath
}
