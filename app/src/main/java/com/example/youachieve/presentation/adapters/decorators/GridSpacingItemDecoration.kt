package com.example.youachieve.presentation.adapters.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GridSpacingItemDecoration(
    private val spanCount: Int = 1,
    private val spacing: Int = 0,
    private val orientation: Int = GridLayoutManager.VERTICAL,
    private val includeEdge: Boolean = false
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        if (orientation == GridLayoutManager.VERTICAL) {
            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount
                outRect.right = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing
            }
            else {
                outRect.left = column * spacing / spanCount
                outRect.right = spacing - (column + 1) * spacing / spanCount

                if (position >= spanCount) {
                    outRect.top = spacing
                }
            }
        }
        else if (orientation == GridLayoutManager.HORIZONTAL) {
            if (includeEdge) {
                outRect.top = spacing - column * spacing / spanCount
                outRect.bottom = (column + 1) * spacing / spanCount

                if (position < spanCount) {
                    outRect.left = spacing;
                }
                outRect.right = spacing
            }
            else {
                outRect.top = column * spacing / spanCount
                outRect.bottom = spacing - (column + 1) * spacing / spanCount

                if (position >= spanCount) {
                    outRect.left = spacing
                }
            }
        }
    }

}