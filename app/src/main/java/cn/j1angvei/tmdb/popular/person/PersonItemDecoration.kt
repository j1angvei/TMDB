package cn.j1angvei.tmdb.popular.person

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
class PersonItemDecoration(context: Context) : RecyclerView.ItemDecoration() {

    private val bigOffset = context.resources.getDimension(R.dimen.offset_big_person).toInt()
    private val smallOffset = context.resources.getDimension(R.dimen.offset_small_person).toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val isLeftColumn = position % 2 == 0
        val isFirstRow = position < 2
        val isLastRow = position >= parent.childCount - 2
        outRect.left = if (isLeftColumn) bigOffset else smallOffset
        outRect.right = if (isLeftColumn) smallOffset else bigOffset
        outRect.top = if (isFirstRow) bigOffset else smallOffset
        outRect.bottom = if (isLastRow) bigOffset else smallOffset
    }
}