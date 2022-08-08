package com.athompson.virgin

import android.content.res.ColorStateList
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
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
fun ImageView.setTint(@ColorRes colorRes: Int) {
    ImageViewCompat.setImageTintList(this, ColorStateList.valueOf(ContextCompat.getColor(context, colorRes)))
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
