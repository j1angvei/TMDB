package cn.j1angvei.tmdb.db

import androidx.room.TypeConverter
import cn.j1angvei.tmdb.list.PageType
import java.util.Date

class DbFieldConverters {
    @TypeConverter
    fun intListToString(intList: List<Int>): String {
        return intList.joinToString("-") { it.toString() }
    }

    @TypeConverter
    fun intListFromString(idStr: String): List<Int> {
        if (idStr.isBlank()) return emptyList()
        return idStr.split("-").map { it.toInt() }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun timestampToDate(timestamp: Long): Date {
        return Date(timestamp)
    }

    @TypeConverter
    fun pageTypeToString(pageType: PageType): String {
        return pageType.name
    }

    @TypeConverter
    fun pageTypeFromString(name: String): PageType {
        return PageType.valueOf(name)
    }
}