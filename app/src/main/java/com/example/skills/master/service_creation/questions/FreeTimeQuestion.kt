package com.example.skills.master.service_creation.questions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.skills.R

@Composable
fun FreeTimeQuestion(
    selectedAnswers: List<Int>,
    onOptionSelected: (selected: Boolean, answer: Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    MultipleChoiceQuestion(
        titleResourceId = R.string.in_my_free_time,
        directionsResourceId = R.string.select_all,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )
}