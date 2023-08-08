package cn.j1angvei.tmdb.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.R
import cn.j1angvei.tmdb.databinding.ItemCreditsCastBinding
import cn.j1angvei.tmdb.loadImage

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
class PersonCreditsRvAdapter :
    ListAdapter<PersonCredits.Cast, PersonCreditsRvAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PersonCredits.Cast>() {
            override fun areItemsTheSame(
                oldItem: PersonCredits.Cast,
                newItem: PersonCredits.Cast
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PersonCredits.Cast,
                newItem: PersonCredits.Cast
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCreditsCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    class ViewHolder(private val binding: ItemCreditsCastBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(cast: PersonCredits.Cast) {
            with(binding) {
                ivPoster.loadImage(cast.fullPoster, R.drawable.ic_poster_placeholder)
                tvTitle.text = cast.title
                tvCharacter.text = tvCharacter.context.getString(
                    R.string.cast_character,
                    cast.character.ifBlank { "未知角色" })
            }
        }
    }

}