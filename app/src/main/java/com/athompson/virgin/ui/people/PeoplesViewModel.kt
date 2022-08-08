package com.athompson.virgin.ui.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.athompson.virgin.data.Person
import com.athompson.virgin.networking.Resource
import com.athompson.virgin.repository.PeopleRepository
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val viewModelPeopleModule = module {
    factory { PeopleViewModel(get()) }
}
class PeopleViewModel(
    private val peopleRepository: PeopleRepository
) : ViewModel() {
    var people =
        liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            emit(peopleRepository.getPeople())
        }

    private val _text = MutableLiveData<String>().apply {
        value = "This is peoples fragment"
    }
    val text: LiveData<String> = _text
}