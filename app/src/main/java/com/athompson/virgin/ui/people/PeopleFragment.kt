package com.athompson.virgin.ui.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import com.athompson.virgin.R
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.FragmentPeopleBinding
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.networking.Status
import com.athompson.virgin.setLayoutManagerVertical
import com.athompson.virgin.showVerticalDividers
import com.athompson.virgin.ui.people.detail.PersonViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent
import org.koin.dsl.module

val peopleFragmentModule = module {
    factory { PeopleFragment() }
}

class PeopleFragment : Fragment(),KoinComponent {
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
        _adapter = PeopleAdapter {
            val personDetailViewModel: PersonViewModel by viewModel()
            personDetailViewModel.selectedPerson.postValue(it)
            val action =
                PeopleFragmentDirections.actionNavigationPeopleToPersonDetailFragment()
            findNavController().navigate(action)
        }
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
}