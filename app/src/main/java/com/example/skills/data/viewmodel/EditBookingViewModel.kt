package com.example.skills.data.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skills.data.Master
import java.time.LocalDate
import java.time.LocalTime

class BookingViewModel: ViewModel() {
    var data1: MutableLiveData<Master> = MutableLiveData() // selected master
    var data2: MutableLiveData<SingleService> = MutableLiveData() // service id
    var data3: MutableLiveData<LocalDate> = MutableLiveData() // date
    var data4: MutableLiveData<LocalTime> = MutableLiveData() // time
    var data5: MutableLiveData<String> = MutableLiveData() // comment (confirm)
}

data class SingleService(
    val serviceId: Long,
    var name: String,
    var description: String,
    var price: Int,
    var duration: Int,
    // var address: String? = null
)
