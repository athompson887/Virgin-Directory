package com.athompson.virgin.ui.people
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers

class PeopleViewModel(
    private val peopleRepository: PeopleRepository
) : ViewModel() {
    var people =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(peopleRepository.getPeople())
        }
}