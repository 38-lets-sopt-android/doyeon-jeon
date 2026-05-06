package com.example.letssopt.core.common.extension

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

fun Modifier.verticalThumbnail(maxWidth: Boolean = false): Modifier =
    this
        .clip(RoundedCornerShape(10.dp))
        .then(
            if (maxWidth) Modifier.fillMaxWidth() else Modifier.width(100.dp)
        )
        .aspectRatio(10 / 15f)
