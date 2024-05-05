package com.example.skills.client.components.a

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skills.data.Master
import com.example.skills.master.components.d.SingleService
import java.time.LocalDate
import java.time.LocalTime

class BookingViewModel: ViewModel() {
    var data1: MutableLiveData<Master> = MutableLiveData() // selected master
    var data2: MutableLiveData<SingleService> = MutableLiveData() // service id
    val data3: MutableLiveData<LocalDate> = MutableLiveData() // date
    val data4: MutableLiveData<LocalTime> = MutableLiveData() // time
    val data5: MutableLiveData<String> = MutableLiveData() // comment (confirm)
}