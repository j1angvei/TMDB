package cn.j1angvei.tmdb.list

import androidx.room.Entity

@Entity("page_key", primaryKeys = ["type", "itemId"])
data class PageKey(
    val type: PageType,
    val itemId: Int,
    val curPage: Int,
    val totalPage: Int,
    val prevPage: Int? = if (curPage <= 1) null else curPage - 1,
    val nextPage: Int? = if (curPage == totalPage) null else curPage + 1,
    val createdAt: Long = System.currentTimeMillis()
)