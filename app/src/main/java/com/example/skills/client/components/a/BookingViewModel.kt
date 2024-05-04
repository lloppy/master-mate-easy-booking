package com.example.skills.client.components.a

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BookingViewModel: ViewModel() {
    val data1: MutableLiveData<String> = MutableLiveData() // service id
    val data2: MutableLiveData<String> = MutableLiveData() // date
    val data3: MutableLiveData<String> = MutableLiveData() // time
    val data4: MutableLiveData<String> = MutableLiveData() // comment (confirm)
}