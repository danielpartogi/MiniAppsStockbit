package com.apelgigit.commons

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private var mLinearLayoutManager: LinearLayoutManager?) :
    RecyclerView.OnScrollListener() {
    private var previousTotal = 0
    private var loading = true
    private val visibleThreshold =
        3 //
    var firstVisibleItem = 0
    var visibleItemCount = 0
    var totalItemCount = 0
    private var currentPage = 0
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (mLinearLayoutManager == null) mLinearLayoutManager = recyclerView.layoutManager as? LinearLayoutManager
        visibleItemCount = recyclerView.childCount
        totalItemCount = mLinearLayoutManager?.itemCount ?: 0
        firstVisibleItem = mLinearLayoutManager?.findFirstVisibleItemPosition() ?: 0
        if (loading) {
            if (totalItemCount != previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        val itemCount = totalItemCount - visibleItemCount
        if (!loading &&  itemCount
            <= firstVisibleItem + visibleThreshold && itemCount >= 0
        ) {
            currentPage += 1
            onLoadMore(currentPage)
            loading = true
        }
    }

    abstract fun onLoadMore(currentPage: Int)

    fun resetState(){
        currentPage = 0
        previousTotal = 0
    }
}


