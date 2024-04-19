package com.example.skills.master.creatingService

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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.skills.master.components.ModalBottomSheetAdd
import com.example.skills.master.dimen
import com.example.skills.ui.theme.blackMaterial
import com.example.skills.ui.theme.greenMaterial
import com.example.skills.ui.theme.orangeMaterial
import com.example.skills.ui.theme.whiteMaterial

@Composable
fun MasterCreateServiceScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(greenMaterial)
            .padding(top = 32.dp, start = 12.dp, end = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    )
    {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f)
                .clip(shape = RoundedCornerShape(dimen))
                .background(blackMaterial)
        ) {
            ModalBottomSheetAdd(navController)
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.2f),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.42f)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(dimen))
                    .background(whiteMaterial)
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
    }
}

