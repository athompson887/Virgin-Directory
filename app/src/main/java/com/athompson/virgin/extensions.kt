package com.athompson.virgin

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.setLayoutManagerVertical()
{
    this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
}
fun RecyclerView.setLayoutManagerHorizontal()
{
    this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
}


fun RecyclerView.showVerticalDividers() {
    this.addItemDecoration(
        DividerItemDecoration(
            this.context,
            DividerItemDecoration.VERTICAL
        )
    )
}

fun RecyclerView.showHorizontalDividers() {
    this.addItemDecoration(
        DividerItemDecoration(
            this.context,
            DividerItemDecoration.HORIZONTAL
        )
    )
}
