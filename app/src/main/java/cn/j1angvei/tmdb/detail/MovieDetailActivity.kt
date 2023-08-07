package cn.j1angvei.tmdb.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import cn.j1angvei.tmdb.NumberUtils
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.databinding.ActivityMovieDetailBinding
import cn.j1angvei.tmdb.loadImage
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
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
    private val castCrewAdapter = CastCrewAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = viewModel.movieTitle
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvCastCrew.layoutManager = LinearLayoutManager(this)
        binding.rvCastCrew.adapter = castCrewAdapter

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
                StringBuilder().append("上映日期：").append(releaseSdf.format(detail.releaseDate))
                    .append(" / ").append(detail.genres.joinToString(separator = " ") { it.name })
                    .append(" / 成本：").append(NumberUtils.convertDollars(detail.budget))
                    .append(" / 票房：").append(NumberUtils.convertDollars(detail.revenue))
                    .append(" / 时长：").append(detail.runtime).append("分钟").toString()
            tvExtraInfo.text = extraInfo
            tvOverviewHeader.isVisible = true
            tvOverview.text = detail.overview
            val castCrewList = mutableListOf<Any>()
            if (detail.credits.cast.isNotEmpty()) {
                castCrewList.add(CastCrewHeader("演员", detail.credits.cast.size))
                castCrewList.addAll(detail.credits.cast)
            }
            if (detail.credits.crew.isNotEmpty()) {
                castCrewList.add(CastCrewHeader("职员", detail.credits.crew.size))
                castCrewList.addAll(detail.credits.crew)
            }
            castCrewAdapter.submitList(castCrewList)
        }
    }

    private fun showErrorHint() {
        Log.d(TAG, "showErrorHint: ")
        Snackbar.make(
            binding.detailContainer, "获取电影详情失败，是否重试？", Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("是") { viewModel.fetchMovieDetail() }
            show()
        }
    }
}