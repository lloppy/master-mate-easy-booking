package com.example.skills.master.service_creation.questions

import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.skills.R

@Composable
fun MultipleChoiceQuestion(
    @StringRes titleResourceId: Int,
    @StringRes directionsResourceId: Int,
    onOptionSelected: (selected: Boolean, answer: Int) -> Unit,

    modifier: Modifier = Modifier,
) {
    QuestionWrapper(
        modifier = modifier,
        titleResourceId = titleResourceId,
        directionsResourceId = directionsResourceId,
    ) {
        Log.e("log", "$onOptionSelected")
        ProfileInputScreen()
    }
}


@Composable
fun ProfileInputScreen() {
    Column(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()) {
        val name = remember { mutableStateOf("") }
        val website = remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text(stringResource(id = R.string.profile_name)) },
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(modifier = Modifier.height(36.dp))

        OutlinedTextField(
            value = website.value,
            onValueChange = { website.value = it },
            label = { Text(stringResource(id = R.string.website)) },
            modifier = Modifier.fillMaxWidth()

        )
    }
}
