package cn.j1angvei.tmdb.list.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.databinding.ItemPopularMovieBinding
import cn.j1angvei.tmdb.loadImage
import java.text.SimpleDateFormat
import java.util.Locale

class MovieListAdapter : PagingDataAdapter<Movie, MovieListAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
                return oldItem == newItem
            }
        }

        private val yearSdf = SimpleDateFormat("yyyy", Locale.CHINA)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemPopularMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie?) {
            movie ?: return
            with(binding) {
                ivProfile.loadImage(movie.fullPoster, R.drawable.ic_poster_placeholder)
                val year = yearSdf.format(movie.releaseDate)
                tvTitle.text = itemView.context.getString(R.string.title_year, movie.title, year)
                rbRating.rating = movie.voteAverage
                tvRating.text = movie.voteAverage.toString()
                tvOverview.text = movie.overview.ifBlank { "暂无剧情简介，欢迎补充" }
            }
        }
    }
}