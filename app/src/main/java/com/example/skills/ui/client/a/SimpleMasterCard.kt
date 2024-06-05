package com.example.skills.ui.client.a

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import com.example.skills.R
import com.example.skills.data.api.MasterForClient
import com.example.skills.data.viewmodel.MY_LOG
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.data.viewmodel.route.BookingViewModel


@Composable
fun SimpleMasterCard(
    master: MasterForClient,
    navigateToSelectedMasterProfile: () -> Unit,
    bookingViewModel: BookingViewModel,
    mainViewModel: MainViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(88.dp)
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Center
    ) {
        val painter =
            if (master.profilePictureId != null) {
                try {
                    mainViewModel.getImageById(master.profilePictureId) { successful ->
                        if (successful) {
                            Log.e(MY_LOG, "img is successful")

                        } else {
                            Log.e(MY_LOG, "img is error")

                        }
                    }
                } catch (e: Exception) {
                    Log.e(MY_LOG, "img is error")

                }


                painterResource(id = R.drawable.master)
                // painterResource(id = )
            } else painterResource(id = R.drawable.master)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painter,
                    contentDescription = "icon",
                    modifier = Modifier.height(52.dp),
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = master.fullName.toString(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            IconButton(onClick = {
                bookingViewModel.data1 = MutableLiveData(master)
                Log.e(
                    "bookingViewModel",
                    "SimpleMasterCard bookingViewModel fullName is ${bookingViewModel.data1.value!!.fullName}"
                )
                navigateToSelectedMasterProfile.invoke()
            }) {
                Icon(Icons.AutoMirrored.Filled.ArrowForwardIos, contentDescription = "icon")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray, thickness = 1.dp)
    }
}