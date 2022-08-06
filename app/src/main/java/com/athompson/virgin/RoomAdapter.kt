package com.athompson.virgin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.data.Room
import com.athompson.virgin.databinding.AdapterRoomBinding
import javax.inject.Inject

class RoomAdapter @Inject constructor() : RecyclerView.Adapter<RoomViewHolder>() {

    var roomList = mutableListOf<Room>()

    fun setRooms(rooms: List<Room>) {
        this.roomList = rooms.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterRoomBinding.inflate(inflater, parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {

        val room = roomList[position]
        holder.binding.name.text = room.id
    }

    override fun getItemCount(): Int {
        return roomList.size
    }
}

class RoomViewHolder(val binding: AdapterRoomBinding) : RecyclerView.ViewHolder(binding.root) {

}