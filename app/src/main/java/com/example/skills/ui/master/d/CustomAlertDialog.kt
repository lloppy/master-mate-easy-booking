package com.example.skills.ui.master.d

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumTouchTargetEnforcement
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    onDismiss: () -> Unit,
    onExit: () -> Unit,
    title: String,
    description: String
) {
    Dialog(
        onDismissRequest = { onDismiss() }, properties = DialogProperties(
            dismissOnBackPress = false, dismissOnClickOutside = false
        )
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp)
                .height(IntrinsicSize.Min)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
            ) {
                Text(
                    text = title,
                    modifier = Modifier
                        .padding(8.dp, 16.dp, 8.dp, 2.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = description,
                    modifier = Modifier
                        .padding(8.dp, 2.dp, 8.dp, 14.dp)
                        .align(Alignment.CenterHorizontally)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp,
                    lineHeight = 16.sp
                )
                Divider(
                    color = Color.LightGray, modifier = Modifier
                        .fillMaxWidth()
                        .width(1.dp)
                )
                Row(Modifier.padding(top = 0.dp)) {
                    CompositionLocalProvider(
                        LocalMinimumTouchTargetEnforcement provides false,
                    ) {
                        TextButton(
                            onClick = { onDismiss() },
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                                .weight(1F)
                                .border(0.dp, Color.Transparent)
                                .height(52.dp),
                            shape = RoundedCornerShape(0.dp),
                            contentPadding = PaddingValues(0.dp)
                        ) {
                            Text(text = "Отмена", color = Color.Red, fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                    Divider(
                        color = Color.LightGray, modifier =
                        Modifier
                            .fillMaxHeight()
                            .width(1.dp)
                    )
                    CompositionLocalProvider(
                        LocalMinimumTouchTargetEnforcement provides false,
                    ) {
                        TextButton(
                            onClick = {
                                onExit.invoke()
                            },
                            Modifier
                                .fillMaxWidth()
                                .padding(0.dp)
                                .weight(1F)
                                .border(0.dp, color = Color.Transparent)
                                .height(52.dp),
                            shape = RoundedCornerShape(0.dp),
                            contentPadding = PaddingValues()
                        ) {
                            Text(text = "Подтвердить", color = Color(0, 122, 255), fontSize = 18.sp)
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                    }
                }
            }
        }
    }
}