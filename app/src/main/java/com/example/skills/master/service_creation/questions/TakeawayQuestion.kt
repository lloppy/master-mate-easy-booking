package com.example.skills.master.service_creation.questions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.skills.R

@Composable
fun TakeawayQuestion(
    dateInMillis: Long?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    DateQuestion(
        titleResourceId = R.string.takeaway,
        directionsResourceId = R.string.select_date,
        dateInMillis = dateInMillis,
        onClick = onClick,
        modifier = modifier,
    )
}