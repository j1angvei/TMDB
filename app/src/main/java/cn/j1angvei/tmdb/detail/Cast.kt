package cn.j1angvei.tmdb.detail

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
)