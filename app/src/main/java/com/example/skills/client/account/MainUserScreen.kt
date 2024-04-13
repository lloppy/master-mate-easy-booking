package com.example.skills.client.account

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun MainUserScreen() {
    val dimen = 60.dp

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(top= 32.dp, bottom = 32.dp, start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.33f)
            .clip(shape = RoundedCornerShape(dimen, dimen, dimen, dimen))
            .background(Color.Green)) {
        }

        Row(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxWidth(0.42f)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(dimen, dimen, dimen, dimen))
                .background(Color.Gray)) {
            }
            Column(modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight()
                .clip(shape = RoundedCornerShape(dimen, dimen, dimen, dimen))
                .background(Color.Blue)) {

            }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .clip(shape = RoundedCornerShape(dimen, dimen, dimen, dimen))
            .background(Color.White)) {

        }
    }
}


@Preview
@Composable
fun prevUserScreen() {
    MainUserScreen()
}