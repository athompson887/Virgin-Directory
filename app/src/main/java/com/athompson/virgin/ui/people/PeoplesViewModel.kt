package com.athompson.virgin.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.athompson.virgin.data.Person

class PeoplesViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is peoples fragment"
    }
    val text: LiveData<String> = _text

    val searchPeopleLiveData = MutableLiveData<List<Person>>()
}