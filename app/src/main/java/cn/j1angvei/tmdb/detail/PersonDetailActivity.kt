package cn.j1angvei.tmdb.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.databinding.ActivityPersonDetailBinding
import cn.j1angvei.tmdb.loadImage
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
@AndroidEntryPoint
class PersonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonDetailBinding
    private val viewModel: PersonDetailViewModel by viewModels()
    private val pagerAdapter = PersonCreditsPagerAdapter()
    private val birthdaySdf = SimpleDateFormat("生日：yyyy年MM月dd日", Locale.CHINA)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        title = viewModel.personName
        binding = ActivityPersonDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewPager()

        viewModel.personDetail.observe(this) { detail ->
            detail?.let { updatePersonDetail(it) } ?: showErrorHint()
        }
        lifecycleScope.launch {
            viewModel.fetchPersonDetail()
        }
    }

    private fun setupViewPager() {
        binding.vpCredits.adapter = pagerAdapter
        TabLayoutMediator(binding.creditsTab, binding.vpCredits) { tab, position ->
            tab.text = if (position == 0) "电影" else "电视剧"
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            finish()
            return true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    private fun updatePersonDetail(detail: PersonDetail) {
        with(binding) {
            ivProfile.loadImage(detail.fullProfile, R.drawable.ic_person_placeholder)
            tvName.text = detail.name
            tvOtherNames.text = detail.chineseName()
            tvBirthday.text = birthdaySdf.format(detail.birthday)
            tvPlaceOfBirth.text = detail.placeOfBirth
            tvOverview.text = detail.biography.ifBlank { "暂无人物简介，欢迎补充" }
            tvOverviewHeader.isVisible = true
        }
        pagerAdapter.setData(detail.movieCredits.cast, detail.tvCredits.cast)
    }

    private fun showErrorHint() {
        Log.d(TAG, "showErrorHint: ")
        Snackbar.make(
            binding.container, "获取人物详情失败，是否重试？", Snackbar.LENGTH_INDEFINITE
        ).apply {
            setAction("是") { viewModel.fetchPersonDetail() }
            show()
        }
    }
}