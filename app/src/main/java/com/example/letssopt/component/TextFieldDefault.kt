package com.example.letssopt.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.ui.theme.LETSSOPTTheme

@Composable
fun TextFieldDefault(
    state: TextFieldState,
    placeholder: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    isPassword: Boolean = false,
    inputTransformation: InputTransformation? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    onKeyboardAction: KeyboardActionHandler? = null,
    lineLimits: TextFieldLineLimits = TextFieldLineLimits.SingleLine,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        label?.let {
            Text(
                text = it,
                color = LETSSOPTTheme.colors.textSecondary,
                style = LETSSOPTTheme.typography.textField,
            )
        }

        BasicTextField(
            state = state,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(52.dp)
                .background(
                    color = LETSSOPTTheme.colors.surface,
                    shape = RoundedCornerShape(8.dp)
                ),
            inputTransformation = inputTransformation,
            outputTransformation = if (isPassword) PasswordOutputTransformation else null,
            textStyle = LETSSOPTTheme.typography.textField.copy(color = LETSSOPTTheme.colors.textPrimary),
            keyboardOptions = keyboardOptions,
            onKeyboardAction = onKeyboardAction,
            lineLimits = lineLimits,
            decorator = { innerTextField ->
                Box(
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 14.dp),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (state.text.isEmpty()) {
                        Text(
                            text = placeholder,
                            color = LETSSOPTTheme.colors.placeHolder,
                            style = LETSSOPTTheme.typography.textField,
                        )
                    }

                    innerTextField()
                }
            },
        )
    }
}

object PasswordOutputTransformation : OutputTransformation {
    override fun TextFieldBuffer.transformOutput() {
        originalText.indices.forEach { index ->
            if (index < originalText.length - 1) {
                replace(index, index + 1, "*")
            }
        }
    }
}

@Preview
@Composable
private fun TextFieldDefaultPreview() {
    LETSSOPTTheme {
        TextFieldDefault(
            state = rememberTextFieldState(),
            placeholder = "이메일 주소를 입력하세요",
            modifier = Modifier.width(240.dp),
            label = "이메일",
        )
    }
}