package cn.j1angvei.tmdb.popular.person

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.databinding.ItemPopularPersonBinding
import cn.j1angvei.tmdb.loadImage
import cn.j1angvei.tmdb.setRadius
import kotlin.math.roundToInt

class PersonListAdapter : PagingDataAdapter<Person, PersonListAdapter.ViewHolder>(diffCallback) {

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Person, newItem: Person): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPopularPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(private val binding: ItemPopularPersonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setRadius(24f)
        }

        fun bind(person: Person?) {
            person ?: return
            with(binding) {
                ivProfile.loadImage(person.fullProfile, R.drawable.ic_person_placeholder)
                tvName.text = person.name
                tvPopularity.text = person.popularity.roundToInt().toString()
            }
        }
    }
}