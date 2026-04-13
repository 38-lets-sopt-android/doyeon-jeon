package com.example.letssopt.core.designsystem.theme

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

private val PretendardFontFamily = FontFamily(
    Font(R.font.pretendard_regular, weight = FontWeight.Normal),
    Font(R.font.pretendard_bold, weight = FontWeight.Bold),
)

private val BoldStyle = LETSSOPTBaseTextStyle.copy(
    fontFamily = PretendardFontFamily,
    fontWeight = FontWeight.Bold,
)

private val RegularStyle = LETSSOPTBaseTextStyle.copy(
    fontFamily = PretendardFontFamily,
    fontWeight = FontWeight.Normal,
)

val defaultLETSSOPTTypography = LETSSOPTTypography(
    logo = BoldStyle.copy(fontSize = 36.sp),
    h1 = BoldStyle.copy(fontSize = 24.sp),
    h2 = BoldStyle.copy(fontSize = 20.sp),
    body = RegularStyle.copy(fontSize = 16.sp),
    caption = RegularStyle.copy(fontSize = 13.sp),
    button = BoldStyle.copy(fontSize = 16.sp),
)

val LocalLETSSOPTTypographyProvider = staticCompositionLocalOf { defaultLETSSOPTTypography }
