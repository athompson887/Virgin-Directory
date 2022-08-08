package com.athompson.virgin.ui.people.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.athompson.virgin.repository.PeopleRepository
import org.koin.dsl.module

val viewModelPersonModule = module {
    factory { PersonViewModel(get()) }
}
class PersonViewModel(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is peoples detail fragment"
    }
    val text: LiveData<String> = _text
}