package com.athompson.virgin

import java.text.SimpleDateFormat
import java.util.*

fun formatDateString(str:String):String
{
    var res = ""
    val formatter = SimpleDateFormat("yyyy-MM-dd")
    val date: Date? = formatter.parse(str)
    if(date!=null) {
        val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        val formattedDate = dateFormatter.format(date)
        res = "$formattedDate"
    }
    return res
}