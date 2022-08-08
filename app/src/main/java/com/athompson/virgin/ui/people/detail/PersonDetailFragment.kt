package com.athompson.virgin.ui.people.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.athompson.virgin.R
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.FragmentPeopleBinding
import com.athompson.virgin.databinding.FragmentPersonDetailBinding
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.networking.Status
import com.athompson.virgin.ui.people.PeopleFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

val personDetailFragmentModule = module {
    factory { PersonDetailFragment() }
}

class PersonDetailFragment : Fragment() {
    private val personDetailViewModel: PersonViewModel by viewModel()
    private lateinit var binding: FragmentPersonDetailBinding

    companion object {
        var sharedPerson: Person? = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_detail, container, false)
        binding.viewModel = personDetailViewModel
        initialiseUIElements()
        initialiseObservers()
        return binding.root
    }

    private fun initialiseUIElements() {
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                }
            }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    private fun initialiseObservers() {
        binding.text
        personDetailViewModel.text.observe(viewLifecycleOwner) {
            binding.text.text = sharedPerson?.firstName?:""
        }
    }
}