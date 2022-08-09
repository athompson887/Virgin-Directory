package com.athompson.virgin

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("SimpleDateFormat")
fun String.formatDateString():String
{
    var res = ""
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val date: Date? = formatter.parse(this)
    if(date!=null) {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormatter.format(date)
        res = formattedDate
    }
    return res
}

fun RecyclerView.setLayoutManagerVertical()
{
    this.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
}

fun RecyclerView.showVerticalDividers() {
    this.addItemDecoration(
        DividerItemDecoration(
            this.context,
            DividerItemDecoration.VERTICAL
        )
    )
}
