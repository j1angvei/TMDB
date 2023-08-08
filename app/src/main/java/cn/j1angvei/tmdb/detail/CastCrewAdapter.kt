package cn.j1angvei.tmdb.detail

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.Navigator
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.databinding.ItemCastCrewHeaderBinding
import cn.j1angvei.tmdb.databinding.ItemMovieCastBinding
import cn.j1angvei.tmdb.databinding.ItemMovieCrewBinding
import cn.j1angvei.tmdb.inflateView
import cn.j1angvei.tmdb.loadImage

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

        private val diffCallback = object : DiffUtil.ItemCallback<Any>() {
            override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
                if (oldItem::class != newItem::class) {
                    return false
                }
                return when (oldItem) {
                    is Cast -> oldItem.id == (newItem as Cast).id
                    is Crew -> oldItem.id == (newItem as Crew).id
                    is CastCrewHeader -> oldItem.title == (newItem as? CastCrewHeader)?.title
                    else -> false
                }
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (val item = getItem(position)) {
            is Cast -> VIEW_TYPE_CAST
            is Crew -> VIEW_TYPE_CREW
            is CastCrewHeader -> VIEW_TYPE_HEADER
            else -> throw IllegalArgumentException("illegal item $item")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_CAST -> CastViewHolder(parent)
            VIEW_TYPE_CREW -> CrewViewHolder(parent)
            VIEW_TYPE_HEADER -> HeaderViewHolder(parent)
            else -> throw IllegalArgumentException("invalid viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CastViewHolder -> holder.bind(getItem(position) as? Cast)
            is CrewViewHolder -> holder.bind(getItem(position) as? Crew)
            is HeaderViewHolder -> holder.bind(getItem(position) as? CastCrewHeader)
        }
    }

    class CastViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflateView(R.layout.item_movie_cast)) {
        private val binding = ItemMovieCastBinding.bind(itemView)
        fun bind(cast: Cast?) {
            binding.root.isVisible = cast != null
            cast ?: return
            val ctx = binding.root.context
            binding.root.setOnClickListener {
                Navigator.toPersonDetail(ctx, cast.id, cast.name)
            }
            binding.ivProfile.loadImage(cast.fullProfile, R.drawable.ic_person_placeholder)
            binding.tvName.text = cast.name
            binding.tvJob.text =
                ctx.getString(R.string.cast_job, if (cast.isMale) "Actor" else "Actress")
            val character = cast.character.ifBlank { "未知角色" }
            binding.tvCharacter.text = ctx.getString(R.string.cast_character, character)
        }
    }

    class CrewViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflateView(R.layout.item_movie_crew)) {
        private val binding = ItemMovieCrewBinding.bind(itemView)
        fun bind(crew: Crew?) {
            binding.root.isVisible = crew != null
            crew ?: return
            binding.ivProfile.loadImage(crew.fullProfile, R.drawable.ic_person_placeholder)
            binding.tvName.text = crew.name
            binding.tvJob.text = crew.job.ifBlank { "工作未知" }
            binding.tvDepartment.text = crew.department.ifBlank { "部门未知" }
        }
    }

    class HeaderViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(parent.inflateView(R.layout.item_cast_crew_header)) {
        private val binding = ItemCastCrewHeaderBinding.bind(itemView)
        fun bind(header: CastCrewHeader?) {
            binding.root.isVisible = header != null
            header ?: return
            binding.tvText.text = header.title
            binding.tvCount.text = header.count.toString()
        }
    }

}