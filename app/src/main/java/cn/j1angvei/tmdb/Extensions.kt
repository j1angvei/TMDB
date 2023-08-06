package cn.j1angvei.tmdb

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, @DrawableRes placeholder: Int) {
    Glide.with(context).load(url).placeholder(placeholder).into(this)
}