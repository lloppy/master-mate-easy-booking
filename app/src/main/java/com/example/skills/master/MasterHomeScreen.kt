package com.example.skills.master

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.skills.ui.theme.backgroundMaterial
import com.example.skills.ui.theme.blackMaterial
import com.example.skills.ui.theme.greenMaterial
import com.example.skills.ui.theme.orangeMaterial

@Composable
fun MainMasterScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundMaterial)
            .padding(top = 32.dp, bottom = 32.dp, start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        //GreetingText(name = Repository.currentUser.name)

        Divider()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .clip(shape = RoundedCornerShape(dimen))
                .background(blackMaterial)
        ) {
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.42f)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(dimen))
                    .background(greenMaterial)
            ) {
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(dimen))
                    .background(orangeMaterial)
            ) {

            }
        }

        Column(
            modifier = Modifier
                .fillMaxHeight(0.6f)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(dimen))
                .background(Color.White)
        ) {}
    }
}

@Composable
fun GreetingText(name: String) {
    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Gray,
                    fontSize = 24.sp
                )
            ) { append("Welcome, ") }
            withStyle(style = SpanStyle(color = Color.Black, fontSize = 24.sp)) { append(name) }
        },
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(8.dp)
    )
}

val dimen = 22.dp
