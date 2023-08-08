package cn.j1angvei.tmdb.detail

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.databinding.ActivityPersonDetailBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
@AndroidEntryPoint
class PersonDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPersonDetailBinding
    private val viewModel: PersonDetailViewModel by viewModels()
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
        val adapter = PersonCreditsPagerAdapter()
        binding.vpCredits.adapter = adapter
        TabLayoutMediator(binding.creditsTab, binding.vpCredits) { tab, position ->
            tab.text = PersonCreditsType.values()[position].text
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
        Log.d(TAG, "updatePersonDetail: $detail")
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