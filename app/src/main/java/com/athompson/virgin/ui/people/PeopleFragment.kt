package com.athompson.virgin.ui.people

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
import com.athompson.virgin.data.Person
import com.athompson.virgin.databinding.FragmentPeopleBinding
import com.athompson.virgin.setLayoutManagerVertical
import com.athompson.virgin.showVerticalDividers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PeopleFragment : Fragment() {

    private var _binding: FragmentPeopleBinding? = null
    private var _adapter: PeopleAdapter? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPeopleBinding.inflate(inflater, container, false)
        initialiseUIElements()
        initialiseObservers()
        return binding.root
    }

    private fun initialiseUIElements() {
        _adapter = PeopleAdapter()
        binding.recycler.setLayoutManagerVertical()
        binding.recycler.showVerticalDividers()
        binding.recycler.itemAnimator = DefaultItemAnimator()
        binding.recycler.adapter = _adapter
    }

    private fun initialiseObservers() {
        val peoplesViewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(
            PeoplesViewModel::class.java)
        val apiInterface = ApiInterface.create().getPeople()
        apiInterface.enqueue( object : Callback<List<Person>> {
            override fun onResponse(call: Call<List<Person>>, response: Response<List<Person>>) {
                if(response.body() != null) {
                    peoplesViewModel.searchPeopleLiveData.postValue(response.body()!!)
                }
            }

            override fun onFailure(call: Call<List<Person>>, t: Throwable) {
            }
        })

        peoplesViewModel.searchPeopleLiveData.observe(viewLifecycleOwner, Observer {
            _adapter?.updateData(it)
        })

        val textView: TextView = binding.textPeople
        peoplesViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}