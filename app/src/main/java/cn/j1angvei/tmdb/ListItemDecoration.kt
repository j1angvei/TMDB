package cn.j1angvei.tmdb

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class ListItemDecoration(private val offset: Int) : RecyclerView.ItemDecoration() {

    private val halfOffset = offset / 2
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val isFirstItem = position == 0
        val isLastItem = position == parent.childCount - 1
        outRect.left = offset
        outRect.right = offset
        outRect.top = if (isFirstItem) offset else halfOffset
        outRect.bottom = if (isLastItem) offset else halfOffset
    }
}