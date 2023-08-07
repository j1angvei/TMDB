package cn.j1angvei.tmdb

import android.util.Log

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
object NumberUtils {
    private const val THE_THOUSAND = 10000
    private const val ONE_MILLION = 1000000
    private const val TEN_BILLION = 100000000

    fun convertDollars(amount: Long): String {
        val ret = if (amount > TEN_BILLION) {
            "${amount / TEN_BILLION}亿美元"
        } else if (amount > ONE_MILLION) {
            "${amount / ONE_MILLION}百万美元"
        } else if (amount > THE_THOUSAND) {
            "${amount / THE_THOUSAND}万美元"
        } else if (amount > 0) {
            "${amount}美元"
        } else {
            "未知"
        }
        Log.d(TAG, "convertDollars: amount $amount, ret $ret")
        return ret
    }
}