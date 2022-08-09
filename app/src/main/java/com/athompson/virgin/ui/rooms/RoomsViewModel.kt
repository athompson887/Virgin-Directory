package com.athompson.virgin.ui.rooms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.repository.RoomRepository
import kotlinx.coroutines.Dispatchers

class RoomsViewModel(
    private val roomRepository: RoomRepository
) : ViewModel() {

    var rooms =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(roomRepository.getRooms())
    }

    private val _text = MutableLiveData<String>().apply {
        value = "This is rooms fragment"
    }
    val text: LiveData<String> = _text
}