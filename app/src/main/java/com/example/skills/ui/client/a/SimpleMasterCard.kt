package com.example.skills.ui.client.a

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForwardIos
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
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

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (master.profileImage != null) {
                    Image(
                        bitmap = master.profileImage!!.asImageBitmap(),
                        contentDescription = "profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(52.dp)
                            .clip(CircleShape),
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.master),
                        contentDescription = "icon",
                        modifier = Modifier.height(52.dp),
                        tint = Color.Unspecified
                    )
                }
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