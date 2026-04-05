package com.example.letssopt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.component.ButtonPrimary
import com.example.letssopt.component.LogoText
import com.example.letssopt.component.TextFieldDefault
import com.example.letssopt.ui.theme.LETSSOPTTheme

class LoginActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val emailState = rememberTextFieldState()
            val passwordState = rememberTextFieldState()
            val loginBtnEnabled = emailState.text.isNotBlank() && passwordState.text.isNotBlank()

            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        emailState = emailState,
                        passwordState = passwordState,
                        onRegisterBtnClick = {},
                        onLoginBtnClick = {},
                        loginBtnEnabled = loginBtnEnabled,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }
}

@Composable
fun LoginScreen(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onRegisterBtnClick: () -> Unit,
    onLoginBtnClick: () -> Unit,
    loginBtnEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        LogoText(
            modifier = Modifier.padding(top = 60.dp, bottom = 26.dp)
        )

        Text(
            text = "이메일로 로그인",
            modifier = Modifier.align(Alignment.Start),
            color = LETSSOPTTheme.colors.textPrimary,
            style = LETSSOPTTheme.typography.h2,
        )

        Spacer(Modifier.height(36.dp))

        TextFieldDefault(
            state = emailState,
            placeholder = "이메일 주소를 입력하세요",
            label = "이메일",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            onKeyboardAction = {
                focusManager.moveFocus(FocusDirection.Next)
            },
        )

        Spacer(Modifier.height(36.dp))

        TextFieldDefault(
            state = passwordState,
            placeholder = "비밀번호를 입력하세요",
            label = "비밀번호",
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            onKeyboardAction = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        )

        Spacer(Modifier.weight(1f))

        Text(
            text = "아직 계정이 없으신가요?  회원가입",
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onRegisterBtnClick,
                )
                .padding(vertical = 20.dp),
            color = LETSSOPTTheme.colors.textSecondary,
            style = LETSSOPTTheme.typography.caption
        )

        ButtonPrimary(
            text = "로그인",
            onClick = onLoginBtnClick,
            enabled = loginBtnEnabled,
        )

        Spacer(Modifier.height(26.dp))
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LETSSOPTTheme {
        LoginScreen(
            emailState = rememberTextFieldState(),
            passwordState = rememberTextFieldState(),
            onRegisterBtnClick = {},
            onLoginBtnClick = {},
            loginBtnEnabled = true,
        )
    }
}