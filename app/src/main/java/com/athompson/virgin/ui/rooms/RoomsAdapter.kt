package com.athompson.virgin.ui.rooms

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.R
import com.athompson.virgin.data.Room
import com.athompson.virgin.databinding.RoomItemBinding
import com.athompson.virgin.formatDateString

class RoomsAdapter : RecyclerView.Adapter<RoomsAdapter.ViewHolder>() {
    private var data = mutableListOf<Room?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.room_item, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val room = data[position]
        val createdAt = room?.createdAt
        holder.binding.createdAt.text = ""
        if(createdAt!=null) {
            val dateStr = room.createdAt.formatDateString()
            if (dateStr.isNotEmpty()) {
                holder.binding.createdAt.text = "Created : $dateStr"
            } else {
                holder.binding.createdAt.text = ""
            }
        }

        holder.binding.id.text = "ID ${room?.id}"
       if(room?.isOccupied==true) {
           holder.binding.availableIcon.setImageResource(R.drawable.ic_baseline_no_meeting_room_24)
           holder.binding.isOccupied.text = "Room is occupied"
           holder.binding.bookButton.visibility = View.INVISIBLE
        } else {
           holder.binding.availableIcon.setImageResource(R.drawable.ic_baseline_meeting_room_24)
           holder.binding.isOccupied.text = "Room is available"
           holder.binding.bookButton.visibility = View.VISIBLE
        }
        holder.binding.maxOccupancy.text = "Max Ooccupancy ${room?.maxOccupancy}"
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