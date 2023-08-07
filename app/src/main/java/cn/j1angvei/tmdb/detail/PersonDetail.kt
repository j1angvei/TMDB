package cn.j1angvei.tmdb.detail

import java.util.Date


data class PersonDetail(
    val id: Int,
    val biography: String,
    val birthday: Date,
    val gender: Int,
    val name: String,
    val placeOfBirth: String,
    val profilePath: String,
    val tvCredits: PersonCredits<PersonCredits.TvCast>,
    val movieCredits: PersonCredits<PersonCredits.MovieCast>
)