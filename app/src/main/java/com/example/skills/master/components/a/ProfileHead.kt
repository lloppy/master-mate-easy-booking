package com.example.skills.master.components.a

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills.R
import com.example.skills.data.Master
import com.example.skills.data.Role
import com.example.skills.master.components.b.calendar.clickable
import com.example.skills.master.components.d.paddingBetweenText
import com.example.skills.ui.theme.fontFamilyInter

@Composable
fun ProfileHead(master: Master) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    val imageName = master.imageId ?: "master"
    val imageId =
        context.resources.getIdentifier(imageName, "drawable", context.packageName)
    val painter =
        if (imageId != 0) painterResource(id = imageId) else painterResource(id = R.drawable.master)

    val uri = Uri.parse(master.linkCode)
    val intent = Intent(Intent.ACTION_VIEW, uri)

    Column(
        modifier = Modifier.fillMaxWidth().padding(start = 12.dp, end = 12.dp, bottom = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(bottom = 12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painter,
                contentDescription = "Master image",
                Modifier.height(120.dp),
                alignment = Alignment.TopCenter
            )
        }
        Text(
            text = master.description,
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
                modifier = Modifier
                    .clickable {
                        expanded = !expanded
                    })
        }
        if (expanded) {
            Spacer(modifier = Modifier.height(paddingBetweenText))
            Text(
                text = master.address,
                fontSize = 14.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
//                lineHeight = 18.sp,
                color = Color.Black,
                modifier = Modifier.padding(start = 8.dp, end = 8.dp)
            )
        }
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
                text = master.linkCode,
                fontSize = 14.sp,
                fontFamily = fontFamilyInter,
                fontWeight = FontWeight.Normal,
                textAlign = TextAlign.Center,
//                lineHeight = 18.sp,
                color = Color(0, 122, 255),
                modifier = Modifier
                    .padding(start = 8.dp)
                    .clickable {
                        context.startActivity(intent)
                    }
            )
        }
    }
}


@Preview
@Composable
fun prev() {
    val imageUrls = listOf(
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
        Uri.parse("https://img.freepik.com/free-vector/aesthetic-background-vector-dried-flower-with-shadow-glitter-design_53876-157555.jpg"),
    )


    ProfileHead(
        Master(
            123,
            "masterivan@gmail.com",
            "Иван",
            "Коссе",
            "79503223232",
            null,
            "12345",
            Role.MASTER,
            "Ведущий мастер в области макияжа и стилистики с более чем десятилетним опытом. Сотрудничал с известными брендами, работала на крупнейших модных показах и обучала начинающих визажистов.",
            "https://t.me/lloppy",
            "г.Екатеринбург, ул.Фонвизина, д.6",
            imageUrls
        )
    )
}