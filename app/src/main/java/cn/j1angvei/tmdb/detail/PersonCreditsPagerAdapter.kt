package cn.j1angvei.tmdb.detail

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.TAG
import cn.j1angvei.tmdb.databinding.ItemPersonCreditsBinding
import cn.j1angvei.tmdb.setRadius

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
class PersonCreditsPagerAdapter : RecyclerView.Adapter<PersonCreditsPagerAdapter.ViewHolder>() {
    companion object {
        private const val PAGE_SIZE = 2
        private const val POSITION_MOVIE = 0
        private const val POSITION_TV = 1
    }

    private val movieCastList = mutableListOf<PersonCredits.Cast>()
    private val tvCastList = mutableListOf<PersonCredits.Cast>()

    fun setData(movieCastList: List<PersonCredits.Cast>, tvCastList: List<PersonCredits.Cast>) {
        Log.d(TAG, "setData: movie $movieCastList, tv $tvCastList")
        this.movieCastList.clear()
        this.tvCastList.clear()
        this.movieCastList.addAll(movieCastList)
        this.tvCastList.addAll(tvCastList)
        notifyItemRangeChanged(0, PAGE_SIZE)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPersonCreditsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return PAGE_SIZE
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = if (position == POSITION_MOVIE) movieCastList else tvCastList
        holder.bindCredit(list)
    }

    class ViewHolder(private val binding: ItemPersonCreditsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = PersonCreditsRvAdapter()

        init {
            binding.root.setRadius(16f)
            val ctx = binding.rvPersonCast.context
            binding.rvPersonCast.addItemDecoration(PersonCreditsItemDecoration(ctx))
            binding.rvPersonCast.layoutManager = GridLayoutManager(ctx, 3)
            binding.rvPersonCast.adapter = adapter
        }

        fun bindCredit(castList: List<PersonCredits.Cast>) {
            adapter.submitList(castList)
        }
    }
}