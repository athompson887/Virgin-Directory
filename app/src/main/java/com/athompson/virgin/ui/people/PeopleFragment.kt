package com.athompson.virgin.ui.people

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.athompson.virgin.R
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.FragmentPeopleBinding
import com.athompson.virgin.databinding.PersonItemBinding
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.networking.Status
import com.athompson.virgin.setLayoutManagerVertical
import com.athompson.virgin.showVerticalDividers
import com.athompson.virgin.ui.people.detail.PersonDetailFragment
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.dsl.module

val peopleFragmentModule = module {
    factory { PeopleFragment() }
}

class PeopleFragment : Fragment() {
    private val peopleViewModel: PeopleViewModel by viewModel()
    private lateinit var binding: FragmentPeopleBinding
    private var _adapter: PeopleAdapter? = null



    private val observer = Observer<Resource<List<Person>>> {
        when (it.status) {
            Status.SUCCESS -> showResult(it)
            Status.ERROR -> showError(it.message!!)
            Status.LOADING -> showLoading()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_people, container, false)
        binding.viewModel = peopleViewModel
        peopleViewModel.people.observe(viewLifecycleOwner, observer)
        initialiseUIElements()
        return binding.root
    }

    private fun initialiseUIElements() {
        _adapter = PeopleAdapter()
        binding.recycler.setLayoutManagerVertical()
        binding.recycler.showVerticalDividers()
        binding.recycler.itemAnimator = DefaultItemAnimator()
        binding.recycler.adapter = _adapter
    }

    private fun showResult(resource: Resource<List<Person>>) {
        resource.data?.let { _adapter?.updateData(it) }
        hideLoading()
    }

    private fun hideLoading() {
        binding.progressLoader.visibility = View.GONE
        binding.progressInfo.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressLoader.visibility = View.VISIBLE
        binding.progressInfo.visibility = View.VISIBLE
        binding.progressInfo.text = getString(R.string.loading_message)
    }

    private fun showError(message: String) {

        binding.progressInfo.text = buildString {
            append(getString(R.string.error_string))
            append(message)
        }
    }

    inner class PeopleAdapter : RecyclerView.Adapter<PeopleAdapter.ViewHolder>(), KoinComponent {
        private var data = mutableListOf<Person?>()

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.person_item, parent, false)
            return ViewHolder(v)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.itemView.setOnClickListener {
                val person = data[position]
                PersonDetailFragment.sharedPerson = person
                if(person!=null) {
                    val action =
                        PeopleFragmentDirections.actionNavigationPeopleToPersonDetailFragment()
                    findNavController().navigate(action)
                }
            }

            Glide.with(requireParentFragment())
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


}