package com.example.skills.ui.client.a

import android.content.Intent
import android.net.Uri
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
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills.R
import com.example.skills.data.api.MasterForClient
import com.example.skills.ui.components.CustomButton
import com.example.skills.ui.master.b.calendar.clickable
import com.example.skills.ui.master.d.paddingBetweenText
import com.example.skills.ui.theme.fontFamilyInter

@Composable
fun ViewMasterHead(master: MasterForClient, navigateToServices: () -> Unit) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }

    if (master.messenger == null) master.messenger = "https://t.me/lloppy"
    val uri = Uri.parse(master.messenger)
    val intent = Intent(Intent.ACTION_VIEW, uri)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, bottom = 6.dp),
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
            if (master.profileImage != null) {
                Image(
                    bitmap = master.profileImage!!.asImageBitmap(),
                    contentDescription = "Master Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    alignment = Alignment.TopCenter
                )
            } else {
                Image(
                    painter = painterResource(id = R.drawable.master),
                    contentDescription = "Master image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape),
                    alignment = Alignment.TopCenter
                )
            }
        }
        if (master?.description == null) master?.description = ""
        Text(
            text = master?.description!!,
            fontSize = 14.sp,
            maxLines = if (expanded) Int.MAX_VALUE else 3,
            fontFamily = fontFamilyInter,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            lineHeight = 18.sp,
            color = Color.Gray,
            modifier = Modifier.padding(start = 12.dp, end = 12.dp)
        )

        if (!expanded) {
            Text(
                text = "... ещё",
                color = Color.Gray,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.clickable { expanded = !expanded })
        }
        if (expanded) {
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = if (master?.address?.city != null) master?.address?.city!! else "",
                fontSize = 14.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
        if (master?.messenger != "https://t.me/lloppy") {
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_link),
                    contentDescription = "link",
                    tint = Color(0, 122, 255),
                )
                Text(
                    text = master?.messenger.toString(),
                    fontSize = 14.sp,
                    fontFamily = fontFamilyInter,
                    fontWeight = FontWeight.Normal,
                    textAlign = TextAlign.Center,
                    color = Color(0, 122, 255),
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .clickable {
                            context.startActivity(intent)
                        }
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val intentPhone = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:${master.phoneNumber}")
            }
            CustomButton(
                navigateTo = { context.startActivity(intentPhone) },
                buttonText = "Позвонить",
                color = Color.Transparent,
                width = 0.5f
            )

            CustomButton(
                navigateTo = {
                    navigateToServices.invoke()
                },
                buttonText = "Записаться"
            )
        }
    }
}
