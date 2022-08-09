package com.athompson.virgin.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import com.athompson.virgin.R
import com.athompson.virgin.data.Room
import com.athompson.virgin.databinding.FragmentRoomsBinding
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.networking.Status
import com.athompson.virgin.setLayoutManagerVertical
import com.athompson.virgin.showVerticalDividers
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.dsl.module

class RoomsFragment : Fragment() {
    private val roomsViewModel: RoomsViewModel by viewModel()
    private lateinit var binding: FragmentRoomsBinding
    private var _adapter:RoomsAdapter? = null

    private val observer = Observer<Resource<List<Room>>> {
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rooms, container, false)
        binding.viewModel = roomsViewModel
        roomsViewModel.rooms.observe(viewLifecycleOwner, observer)
        initialiseUIElements()
        return binding.root
    }

    private fun initialiseUIElements() {
        _adapter = RoomsAdapter()
        binding.recycler.setLayoutManagerVertical()
        binding.recycler.showVerticalDividers()
        binding.recycler.itemAnimator = DefaultItemAnimator()
        binding.recycler.adapter = _adapter
    }

    private fun showResult(resource: Resource<List<Room>>) {
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