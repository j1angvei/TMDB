package cn.j1angvei.tmdb

const val TAG = "TMDB_LOG"

const val TMDB_BASE_URL = "https://api.themoviedb.org/3/"
const val TMDB_IMG_URL = "https://image.tmdb.org/t/p/original"
const val TMDB_PAGE_SIZE = 20
const val TMDB_ACCESS_TOKEN =
    "eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkY2MxODY3MmJlYjI4ZGQ1OTYwODU4YzVjM2UzYjE3NCIsInN1YiI6IjU5MWMwZDA0YzNhMzY4N2E4ZTAwNzBiNCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.nqvUK6XGdPta-JFSNJeuAGGoCW0H9Pa1lhIuAFpA8uQ"

const val DB_CACHE_EXPIRE_DURATION = 1000 * 60 * 10L // 10分钟过期

const val TMDB_START_PAGE = 1

const val CALL_TIME_OUT = 10L // 10s超时

const val EXTRA_MOVIE_ID = "movie_id"
const val EXTRA_MOVIE_TITLE = "movie_title"
const val EXTRA_PERSON_ID = "person_id"
const val EXTRA_PERSON_NAME = "person_name"

