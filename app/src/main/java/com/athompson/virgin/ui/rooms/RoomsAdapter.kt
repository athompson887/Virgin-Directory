package com.athompson.virgin.ui.rooms

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.R
import com.athompson.virgin.data.Room
import com.athompson.virgin.databinding.RoomItemBinding

class RoomsAdapter : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    private var data = mutableListOf<Room?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = data[position]

        holder.itemView.setOnClickListener {

        }
        holder.binding.roomText.text = room?.createdAt
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Room?>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding: RoomItemBinding = RoomItemBinding.bind(itemView)
    }
}