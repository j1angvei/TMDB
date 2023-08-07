package cn.j1angvei.tmdb.detail

import cn.j1angvei.tmdb.TMDB_IMG_URL

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
data class Cast(
    val id: Int,
    val gender: Int,
    val name: String,
    val character: String,
    val order: Int,
    val profilePath: String
) {
    val fullProfile: String
        get() = TMDB_IMG_URL + profilePath
    val isMale: Boolean
        get() = gender == 2
}