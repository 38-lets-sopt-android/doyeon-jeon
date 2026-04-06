package com.example.letssopt.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun ButtonPrimary(
    text: String,
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable(
                onClick = onClick,
                enabled = enabled,
            )
            .fillMaxWidth()
            .heightIn(25.dp)
            .background(if (enabled) LETSSOPTTheme.colors.primaryRed else LETSSOPTTheme.colors.disabled)
            .wrapContentSize(Alignment.Center)
            .padding(all = 16.dp),
        color = if (enabled) LETSSOPTTheme.colors.textPrimary else LETSSOPTTheme.colors.placeHolder,
        style = LETSSOPTTheme.typography.button,
    )
}

private class ButtonPrimaryPreviewProvider : PreviewParameterProvider<Boolean> {
    override val values: Sequence<Boolean>
        get() = sequenceOf(true, false)
}

@Preview
@Composable
private fun ButtonPrimaryPreview(
    @PreviewParameter(ButtonPrimaryPreviewProvider::class) enabled: Boolean,
) {
    LETSSOPTTheme {
        ButtonPrimary(
            text = "로그인",
            onClick = {},
            enabled = enabled,
            modifier = Modifier.width(240.dp)
        )
    }
}