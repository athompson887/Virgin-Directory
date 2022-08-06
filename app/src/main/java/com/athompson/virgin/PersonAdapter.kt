package com.athompson.virgin
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.AdapterPersonBinding
import javax.inject.Inject

class PersonAdapter @Inject constructor() : RecyclerView.Adapter<PersonViewHolder>() {

    var personList = mutableListOf<Person>()

    fun setPeople(movies: List<Person>) {
        this.personList = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterPersonBinding.inflate(inflater, parent, false)
        return PersonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {

        val person = personList[position]
        holder.binding.name.text = person.firstName
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}

class PersonViewHolder(val binding: AdapterPersonBinding) : RecyclerView.ViewHolder(binding.root) {

}