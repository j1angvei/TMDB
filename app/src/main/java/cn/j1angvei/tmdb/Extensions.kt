package cn.j1angvei.tmdb

import android.graphics.Outline
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewOutlineProvider
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.annotation.LayoutRes
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

fun ViewGroup.inflateView(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}
