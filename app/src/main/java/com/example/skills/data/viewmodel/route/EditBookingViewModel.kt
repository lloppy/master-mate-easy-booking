package com.example.skills.data.viewmodel.route

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.skills.data.api.MasterForClient
import com.example.skills.data.entity.Service
import com.example.skills.data.entity.TimeSlot
import com.example.skills.data.roles.User
import java.time.LocalDate
import java.time.LocalTime

class EditBookingViewModel: ViewModel() {
    var data1: MutableLiveData<MasterForClient> = MutableLiveData() // selected master
    var data2: MutableLiveData<Service> = MutableLiveData() // service
    var data3: MutableLiveData<LocalDate> = MutableLiveData() // date
    var data4: MutableLiveData<TimeSlot> = MutableLiveData() // time
    var data5: MutableLiveData<String> = MutableLiveData() // comment (confirm)
}
