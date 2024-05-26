package com.example.skills.data.viewmodel.route

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skills.data.entity.Service
import com.example.skills.data.roles.User
import java.time.LocalDate
import java.time.LocalTime

class EditBookingViewModel: ViewModel() {
    var data1: MutableLiveData<User> = MutableLiveData() // selected master
    var data2: MutableLiveData<Service> = MutableLiveData() // service
    var data3: MutableLiveData<LocalDate> = MutableLiveData() // date
    var data4: MutableLiveData<LocalTime> = MutableLiveData() // time
    var data5: MutableLiveData<String> = MutableLiveData() // comment (confirm)
}
