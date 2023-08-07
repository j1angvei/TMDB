package cn.j1angvei.tmdb.detail

import cn.j1angvei.tmdb.TMDB_IMG_URL

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
data class Crew(
    val id: Int,
    val name: String,
    val profilePath: String,
    val department: String,
    val job: String
) {
    val fullProfile: String
        get() = TMDB_IMG_URL + profilePath
}