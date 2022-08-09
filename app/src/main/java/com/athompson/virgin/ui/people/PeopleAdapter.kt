package com.athompson.virgin.ui.people

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.R
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.PersonItemBinding
import com.athompson.virgin.utility.AppUtility
import com.bumptech.glide.Glide
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class PeopleAdapter(
    private var onItemClicked: ((selectedPerson: Person) -> Unit)
) : RecyclerView.Adapter<PeopleAdapter.ViewHolder>(), KoinComponent {

    private var data = mutableListOf<Person?>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.person_item, parent, false)
        return ViewHolder(v)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            val person = data[position]
            if(person!=null) {
                onItemClicked(person)
            }
        }
        val appUtility : AppUtility by inject()
        Glide.with(appUtility.context)
            .load(data[position]?.avatar)
            .placeholder(R.drawable.ic_baseline_person_24)
            .into(holder.binding.avatarView)

        holder.binding.fullName.text = "${data[position]?.firstName} {${data[position]?.lastName}"
        holder.binding.jobTitle.text = "${data[position]?.jobtitle}"
        holder.binding.email.text = "${data[position]?.email}"
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
