package cn.j1angvei.tmdb

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.databinding.ItemLoadStateBinding

class LoadStateViewHolder(parent: ViewGroup, retry: () -> Unit) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_load_state, parent, false)
) {
    private val binding = ItemLoadStateBinding.bind(itemView)

    init {
        binding.btnRetry.setOnClickListener { retry() }
    }

    fun bind(loadState: LoadState) {
        binding.pbLoading.isVisible = loadState is LoadState.Loading
        binding.tvLoadingHint.isVisible = loadState is LoadState.Loading
        binding.btnRetry.isVisible = loadState is LoadState.Error
    }
}