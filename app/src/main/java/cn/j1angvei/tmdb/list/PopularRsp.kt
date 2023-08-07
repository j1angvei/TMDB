package cn.j1angvei.tmdb.list

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
data class PopularRsp<T>(
    val page: Int,
    val results: List<T>,
    val totalPages: Int
)