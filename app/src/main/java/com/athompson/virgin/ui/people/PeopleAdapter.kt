package com.athompson.virgin.ui.people

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.R
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.PersonItemBinding

class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>() {

    private var data = mutableListOf<Person?>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemView.setOnClickListener {

        }

        holder.binding.firstName.text = data.get(position)?.firstName
    }

    override fun getItemCount(): Int {
        return data.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newData: List<Person?>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }


    inner class ViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        val binding: PersonItemBinding = PersonItemBinding.bind(itemView)
    }
}