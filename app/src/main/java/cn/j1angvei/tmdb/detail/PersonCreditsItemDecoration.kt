package cn.j1angvei.tmdb.detail

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import cn.j1angvei.tmdb.R

/**
 *
 * @author : jiangwei.man
 * @since : 2023/8/7
 **/
class PersonCreditsItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val bigOffset = context.resources.getDimension(R.dimen.offset_big_credits).toInt()
    private val smallOffset = context.resources.getDimension(R.dimen.offset_big_credits).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val isLeftColumn = position % 3 == 0
        val isRightColumn = position % 3 == 2
        val isFirstRow = position < 3
        val isLastRow = position >= parent.childCount - 3
        outRect.left = if (isLeftColumn) bigOffset else smallOffset
        outRect.right = if (isRightColumn) smallOffset else bigOffset
        outRect.top = if (isFirstRow) bigOffset else smallOffset
        outRect.bottom = if (isLastRow) bigOffset else smallOffset
    }
}