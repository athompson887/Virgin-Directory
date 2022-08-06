package com.athompson.virgin

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.AdapterRoomBinding
import javax.inject.Inject

class RoomAdapter @Inject constructor() : RecyclerView.Adapter<RoomViewHolder>() {

    var personList = mutableListOf<Person>()

    fun setRooms(movies: List<Person>) {
        this.personList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterRoomBinding.inflate(inflater, parent, false)
        return RoomViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RoomViewHolder, position: Int) {

        val person = personList[position]
        holder.binding.name.text = person.firstName
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}

class RoomViewHolder(val binding: AdapterRoomBinding) : RecyclerView.ViewHolder(binding.root) {

}