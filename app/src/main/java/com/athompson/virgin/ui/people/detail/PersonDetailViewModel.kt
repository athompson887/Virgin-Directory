package com.athompson.virgin.ui.people.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.athompson.virgin.data.Person
import com.athompson.virgin.repository.PeopleRepository


class PersonDetailViewModel() : ViewModel() {

    val selectedPerson: MutableLiveData<Person> by lazy {
        MutableLiveData<Person>()
    }
}