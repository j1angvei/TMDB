package cn.j1angvei.tmdb.detail

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.databinding.ItemCastCrewHeaderBinding
import cn.j1angvei.tmdb.databinding.ItemMovieCastBinding
import cn.j1angvei.tmdb.databinding.ItemMovieCrewBinding

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
class CastCrewAdapter : ListAdapter<Any, RecyclerView.ViewHolder>(diffCallback) {

    companion object {
        private const val VIEW_TYPE_CAST = 1
        private const val VIEW_TYPE_CREW = 2
        private const val VIEW_TYPE_HEADER = 3
        private const val VIEW_TYPE_DUMMY = 4

        private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                TODO("Not yet implemented")
            }

            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                TODO("Not yet implemented")
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Cast -> VIEW_TYPE_CAST
            is Crew -> VIEW_TYPE_CREW
            is CastCrewHeader -> VIEW_TYPE_HEADER
            else -> VIEW_TYPE_DUMMY
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class CastViewHolder(binding: ItemMovieCastBinding) : RecyclerView.ViewHolder(binding.root)
    class CrewViewHolder(binding: ItemMovieCrewBinding) : RecyclerView.ViewHolder(binding.root)
    class HeaderViewHolder(binding: ItemCastCrewHeaderBinding) :
        RecyclerView.ViewHolder(binding.root)

}