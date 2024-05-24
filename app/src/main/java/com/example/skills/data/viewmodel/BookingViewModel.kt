package com.example.skills.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skills.data.roles.Master
import com.example.skills.data.entity.Service
import java.time.LocalDate
import java.time.LocalTime

class BookingViewModel: ViewModel() {
    var data1: MutableLiveData<Master> = MutableLiveData() // selected master
    var data2: MutableLiveData<Service> = MutableLiveData() // service
    var data3: MutableLiveData<LocalDate> = MutableLiveData() // date
    var data4: MutableLiveData<LocalTime> = MutableLiveData() // time
    var data5: MutableLiveData<String> = MutableLiveData() // comment (confirm)
}