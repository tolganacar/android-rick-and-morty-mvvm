package com.tolganacar.rickmorty.view.rmcharacterlist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RMCharacterListOnScrollListener : RecyclerView.OnScrollListener() {
    abstract fun isLoading(): Boolean
    abstract fun loadMoreItems()

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.layoutManager?.childCount ?: 0
        val totalItemCount = recyclerView.layoutManager?.itemCount ?: 0
        val firstVisibleItemPosition =
            (recyclerView.layoutManager as? LinearLayoutManager)?.findFirstVisibleItemPosition()
                ?: 0

        if (!isLoading()) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0) {
                loadMoreItems()
            }
        }
    }
}