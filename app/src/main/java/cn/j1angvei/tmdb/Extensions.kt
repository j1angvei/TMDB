package cn.j1angvei.tmdb

import android.graphics.Outline
import android.view.View
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun ImageView.loadImage(url: String, @DrawableRes placeholder: Int) {
    Glide.with(context).load(url).placeholder(placeholder).into(this)
}

fun View.setRadius(radius: Float) {
    outlineProvider = object : ViewOutlineProvider() {
        override fun getOutline(view: View?, outline: Outline?) {
            view ?: return
            outline?.setRoundRect(0, 0, view.width, view.height, radius)
        }
    }
    clipToOutline = true
}