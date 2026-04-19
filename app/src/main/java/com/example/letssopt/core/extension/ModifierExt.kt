package com.example.letssopt.core.extension

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

fun Modifier.verticalThumbnail(): Modifier =
    this
        .clip(RoundedCornerShape(10.dp))
        .size(100.dp, 150.dp)
