package com.athompson.virgin.ui.people.detail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.athompson.virgin.R
import com.athompson.virgin.databinding.FragmentPersonDetailBinding
import com.athompson.virgin.formatDateString
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.SimpleDateFormat
import java.util.*

class PersonDetailFragment : Fragment() {
    private val personDetailViewModel: PersonViewModel by viewModel()
    private lateinit var binding: FragmentPersonDetailBinding

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



    @SuppressLint("SimpleDateFormat", "SetTextI18n")
    private fun initialiseObservers() {
        personDetailViewModel.selectedPerson.observe(viewLifecycleOwner) {

            binding.firstNameText.text = it.firstName
            binding.lastNameText.text = it.lastName
            binding.emailText.text = it.email

            Glide.with(this)
                .load(it.avatar)
                .placeholder(R.drawable.ic_baseline_person_24)
                .into(binding.avatarView)

            binding.jobTitle.text =it.jobtitle

            val dateStr = it.createdAt.formatDateString()
            if(dateStr.isNotEmpty()) {
                binding.createdAt.text = "Created : $dateStr"
            }
            else{
                binding.createdAt.text = ""
            }

            val formatter = SimpleDateFormat("yyyy-MM-dd")
            val date:Date? = formatter.parse(it.createdAt)
            if(date!=null) {
                val dateFormatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val formattedDate = dateFormatter.format(date)
                binding.createdAt.text = "Created : $formattedDate"
            }
            else{
                binding.createdAt.text = ""
            }
            binding.favouriteColour.text = "My favourite colour is ${it.favouriteColor}"
            binding.id.text = "ID Number ${it.id}"
        }
    }
}