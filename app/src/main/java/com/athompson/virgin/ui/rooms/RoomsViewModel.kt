package com.athompson.virgin.ui.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RoomsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is rooms fragment"
    }
    val text: LiveData<String> = _text
}