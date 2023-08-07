package cn.j1angvei.tmdb.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.j1angvei.tmdb.NumberUtils
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.databinding.ActivityMovieDetailBinding
import cn.j1angvei.tmdb.loadImage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.Locale

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val yearSdf = SimpleDateFormat("yyyy", Locale.CHINA)
    private val releaseSdf = SimpleDateFormat("yyyy-MM-dd", Locale.CHINA)
    private val viewModel by viewModels<MovieDetailViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = viewModel.movieTitle
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.movieDetail.observe(this) { detail ->
            detail?.let { updateMovieDetail(it) } ?: showErrorHint()
        }
        lifecycleScope.launch {
            viewModel.fetchMovieDetail()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            return true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun updateMovieDetail(detail: MovieDetail) {
        Log.d(TAG, "updateMovieDetail: $detail")
        with(binding) {
            ivPoster.loadImage(detail.fullPoster, R.drawable.ic_poster_placeholder)
            tvTitle.text = detail.title
            val originalTitle =
                if (detail.title == detail.originalTitle) "" else detail.originalTitle
            val year = yearSdf.format(detail.releaseDate)
            tvOriginalTitleYear.text = getString(R.string.title_year, originalTitle, year)
            val extraInfo =
                StringBuilder().append("上映日期：")
                    .append(releaseSdf.format(detail.releaseDate))
                    .append(" / ")
                    .append(detail.genres.joinToString(separator = " ") { it.name })
                    .append(" / 成本：")
                    .append(NumberUtils.convertDollars(detail.budget))
                    .append(" / 票房：")
                    .append(NumberUtils.convertDollars(detail.revenue))
                    .append(" / 时长：")
                    .append(detail.runtime)
                    .append("分钟 >")
                    .toString()
            tvExtraInfo.text = extraInfo
            tvOverview.text = detail.overview
        }
    }

    private fun showErrorHint() {
        Log.d(TAG, "showErrorHint: ")
        Snackbar.make(binding.detailContainer, "获取详情失败", Snackbar.LENGTH_INDEFINITE).apply {
            setAction("重试") { viewModel.fetchMovieDetail() }
            show()
        }
    }
}