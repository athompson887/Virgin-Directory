package com.athompson.virgin.ui.people.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.athompson.virgin.data.Person
import com.athompson.virgin.repository.PeopleRepository
import org.koin.dsl.module

val viewModelPersonModule = module {
    factory { PersonViewModel(get()) }
}
class PersonViewModel(
    private val peopleRepository: PeopleRepository
) : ViewModel() {

    val selectedPerson: MutableLiveData<Person> by lazy {
        MutableLiveData<Person>()
    }
}