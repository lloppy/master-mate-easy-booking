package com.example.skills.master.service_creation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.skills.R
import com.example.skills.master.service_creation.questions.PhotoQuestion

@Composable
fun TakeSelfieQuestion(
    imageUri: Uri?,
    getNewImageUri: () -> Uri,
    onPhotoTaken: (Uri) -> Unit,
    modifier: Modifier = Modifier,
) {
    PhotoQuestion(
        titleResourceId = R.string.selfie_skills,
        imageUri = imageUri,
        getNewImageUri = getNewImageUri,
        onPhotoTaken = onPhotoTaken,
        modifier = modifier,
    )
}
