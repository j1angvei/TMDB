package cn.j1angvei.tmdb.detail

import cn.j1angvei.tmdb.TMDB_IMG_URL
import java.util.Date


data class PersonDetail(
    val id: Int,
    val biography: String,
    val birthday: Date,
    val gender: Int,
    val name: String,
    val alsoKnownAs: List<String>,
    val placeOfBirth: String,
    val profilePath: String,
    val tvCredits: PersonCredits,
    val movieCredits: PersonCredits
) {
    val fullProfile: String
        get() = TMDB_IMG_URL + profilePath

    fun chineseName(): String? {
        alsoKnownAs.forEach { name ->
            for (c in name) {
                if (Character.UnicodeScript.of(c.code) == Character.UnicodeScript.HAN) {
                    return name
                }
            }
        }
        return alsoKnownAs.firstOrNull()
    }
}