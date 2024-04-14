package com.example.skills.master.service_creation.questions

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.skills.R

@Composable
fun SuperheroQuestion(
    selectedAnswer: Superhero?,
    onOptionSelected: (Superhero) -> Unit,
    modifier: Modifier = Modifier,
) {
    SingleChoiceQuestion(
        titleResourceId = R.string.pick_superhero,
        directionsResourceId = R.string.select_one,
        possibleAnswers = listOf(
            Superhero(R.string.spark, null),
            Superhero(R.string.lenz, null),
            Superhero(R.string.bugchaos, null),
            Superhero(R.string.frag, null),
        ),
        selectedAnswer = selectedAnswer,
        onOptionSelected = onOptionSelected,
        modifier = modifier,
    )
}