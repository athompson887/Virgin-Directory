package com.athompson.virgin.ui.rooms

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import com.athompson.virgin.data.ApiInterface
import com.athompson.virgin.data.Room
import com.athompson.virgin.databinding.FragmentRoomsBinding
import com.athompson.virgin.setLayoutManagerVertical
import com.athompson.virgin.showVerticalDividers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RoomsFragment : Fragment() {

    private var _binding: FragmentRoomsBinding? = null
    private var _adapter:RoomsAdapter? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRoomsBinding.inflate(inflater, container, false)
        initialiseUIElements()

        initialiseObservers()
        return binding.root
    }

    private fun initialiseUIElements() {
        _adapter = RoomsAdapter()
        binding.recycler.setLayoutManagerVertical()
        binding.recycler.showVerticalDividers()
        binding.recycler.itemAnimator = DefaultItemAnimator()
        binding.recycler.adapter = _adapter
    }

    private fun initialiseObservers() {
        val roomsViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(RoomsViewModel::class.java)
        val apiInterface = ApiInterface.create().getRooms()
        apiInterface.enqueue( object : Callback<List<Room>> {
            override fun onResponse(call: Call<List<Room>>, response: Response<List<Room>>) {
                if(response.body() != null) {
                    roomsViewModel.searchRoomsLiveData.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Room>>, t: Throwable) {
            }
        })

        roomsViewModel.searchRoomsLiveData.observe(viewLifecycleOwner, Observer {
            _adapter?.updateData(it)
        })

        val textView: TextView = binding.textRooms
        roomsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}