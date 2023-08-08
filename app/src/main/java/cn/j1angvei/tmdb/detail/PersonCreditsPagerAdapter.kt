package cn.j1angvei.tmdb.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.databinding.ItemPersonCreditsBinding

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/8
 **/
class PersonCreditsPagerAdapter : RecyclerView.Adapter<PersonCreditsPagerAdapter.ViewHolder>() {
    private val creditsList = PersonCreditsType.values()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemPersonCreditsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return creditsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindCredit(creditsList[position])
    }

    class ViewHolder(private val binding: ItemPersonCreditsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bindCredit(credit: PersonCreditsType) {
            binding.tvText.text = credit.text
        }
    }
}