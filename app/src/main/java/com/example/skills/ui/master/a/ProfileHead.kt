package com.example.skills.ui.master.a

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.skills.R
import com.example.skills.data.roles.User
import com.example.skills.data.viewmodel.MainViewModel
import com.example.skills.ui.master.b.calendar.clickable
import com.example.skills.ui.master.d.paddingBetweenText
import com.example.skills.ui.theme.fontFamilyInter

@Composable
fun ProfileHead(user: User) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    val imageFile = user.master?.profileImage
    val painter = if (imageFile != null) {
        rememberAsyncImagePainter(
            model = imageFile,
            placeholder = painterResource(id = R.drawable.ic_bin),
            error = painterResource(id = R.drawable.logo)
        )
    } else {
        painterResource(id = R.drawable.master)
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painter,
                contentDescription = "Master image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp)
                    .clip(CircleShape),
                alignment = Alignment.TopCenter
            )
        }
        if (user.master?.description != null) {
            Text(
                text = user.master!!.description!!,
                fontSize = 14.sp,
                maxLines = if (expanded) Int.MAX_VALUE else 3,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                color = Color.Gray,
                modifier = Modifier.padding(start = 12.dp, end = 12.dp)
            )
        }
        if (user.master?.address?.city != null) {
            if (!expanded) {
                Text(
                    text = "... ещё",
                    color = Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier
                        .clickable {
                            expanded = !expanded
                        })
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(paddingBetweenText))
                Text(
                    text = user.master!!.address.toString(),
                    fontSize = 14.sp,
                    fontFamily = fontFamilyInter,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
//                lineHeight = 18.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp)
                )
            }
        }
        if (user.master?.messenger != null) {
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_link),
                    contentDescription = "messenger link",
                    tint = Color(0, 122, 255),
                )
                Text(
                    text = user.master!!.messenger!!,
                    fontSize = 14.sp,
                    fontFamily = fontFamilyInter,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color(0, 122, 255),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            val uri =  Uri.parse(user.master!!.linkCode)
                            val intent = Intent(Intent.ACTION_VIEW, uri)

                            context.startActivity(intent)
                        }
                )
            }
        }
    }
}
