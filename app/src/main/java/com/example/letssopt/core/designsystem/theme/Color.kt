package com.example.letssopt.core.designsystem.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val PrimaryRed = Color(0xFFE8003C)
val Background = Color(0xFF141414)

val Surface = Color(0xFF2A2A2A)
val White = Color(0xFFFFFFFF)
val TextSecondary = Color(0xFF999999)
val Placeholder = Color(0xFF666666)
val Disabled = Color(0xFF333333)
val Black = Color(0xFF000000)

@Immutable
class LETSSOPTColors(
    val primaryRed: Color,
    val background: Color,
    val surface: Color,
    val white: Color,
    val textSecondary: Color,
    val placeHolder: Color,
    val disabled: Color,
    val black: Color,
)

val defaultLETSSOPTColors = LETSSOPTColors(
    primaryRed = PrimaryRed,
    background = Background,
    surface = Surface,
    white = White,
    textSecondary = TextSecondary,
    placeHolder = Placeholder,
    disabled = Disabled,
    black = Black,
)

val LocalLETSSOPTColorsProvider = staticCompositionLocalOf { defaultLETSSOPTColors }
