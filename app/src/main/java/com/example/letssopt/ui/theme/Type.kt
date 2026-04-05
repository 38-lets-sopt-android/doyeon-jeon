package com.example.letssopt.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.sp
import com.example.letssopt.R

@Immutable
class LETSSOPTTypography(
    val logo: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val body: TextStyle,
    val caption: TextStyle,
    val button: TextStyle,
    val textField: TextStyle,
)

private val LETSSOPTBaseTextStyle = TextStyle(
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None,
    ),
)

private val InterFontFamily = FontFamily(
    Font(R.font.inter_regular, weight = FontWeight.Normal),
    Font(R.font.inter_bold, weight = FontWeight.Bold),
)

private val BoldStyle = LETSSOPTBaseTextStyle.copy(
    fontFamily = InterFontFamily,
    fontWeight = FontWeight.Bold,
)

private val RegularStyle = LETSSOPTBaseTextStyle.copy(
    fontFamily = InterFontFamily,
    fontWeight = FontWeight.Normal,
)

val defaultLETSSOPTTypography = LETSSOPTTypography(
    logo = BoldStyle.copy(fontSize = 36.sp),
    h1 = BoldStyle.copy(fontSize = 24.sp),
    h2 = BoldStyle.copy(fontSize = 20.sp),
    body = RegularStyle.copy(fontSize = 16.sp),
    caption = RegularStyle.copy(fontSize = 13.sp),
    button = BoldStyle.copy(fontSize = 16.sp),
    textField = RegularStyle.copy(fontSize = 14.sp),
)

val LocalLETSSOPTTypographyProvider = staticCompositionLocalOf { defaultLETSSOPTTypography }
