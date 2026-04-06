package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.component.ButtonPrimary
import com.example.letssopt.component.LogoText
import com.example.letssopt.component.TextFieldDefault
import com.example.letssopt.ui.theme.LETSSOPTTheme

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val emailState = rememberTextFieldState()
            val passwordState = rememberTextFieldState()
            val passwordCheckState = rememberTextFieldState()

            val registerEnabled = emailState.text.isNotBlank()
                    && passwordState.text.isNotBlank()
                    && passwordCheckState.text.isNotBlank()

            val context = LocalContext.current

            fun onRegisterClick() {
                when {
                    !Patterns.EMAIL_ADDRESS.matcher(emailState.text).matches() -> {
                        Toast.makeText(context, "올바른 이메일 형식을 입력해주세요", Toast.LENGTH_SHORT).show()
                    }

                    passwordState.text.length !in 8..12 -> {
                        Toast.makeText(context, "비밀번호는 8~12자로 입력해주세요", Toast.LENGTH_SHORT).show()
                    }

                    passwordState.text != passwordCheckState.text -> {
                        Toast.makeText(context, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show()
                    }

                    else -> {
                        Toast.makeText(context, "회원가입에 성공했습니다", Toast.LENGTH_SHORT).show()

                        val intent = Intent()
                            .putExtra(EMAIL_KEY, emailState.text.toString())
                            .putExtra(PASSWORD_KEY, passwordState.text.toString())

                        setResult(RESULT_OK, intent)
                        finish()
                    }
                }
            }

            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterScreen(
                        emailState = emailState,
                        passwordState = passwordState,
                        passwordCheckState = passwordCheckState,
                        onRegisterClick = { onRegisterClick() },
                        registerEnabled = registerEnabled,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RegisterScreen(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    passwordCheckState: TextFieldState,
    onRegisterClick: () -> Unit,
    registerEnabled: Boolean,
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
            text = "회원가입",
            modifier = Modifier.align(Alignment.Start),
            color = LETSSOPTTheme.colors.textPrimary,
            style = LETSSOPTTheme.typography.h2,
        )

        Spacer(Modifier.height(36.dp))

        TextFieldDefault(
            state = emailState,
            placeholder = "이메일 주소를 입력하세요",
            label = "이메일",
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            onKeyboardAction = {
                focusManager.moveFocus(FocusDirection.Next)
            },
        )

        Spacer(Modifier.height(18.dp))

        TextFieldDefault(
            state = passwordState,
            placeholder = "비밀번호를 입력하세요",
            label = "비밀번호",
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Password
            ),
            onKeyboardAction = {
                focusManager.moveFocus(FocusDirection.Next)
            },
        )

        Spacer(Modifier.height(18.dp))

        TextFieldDefault(
            state = passwordCheckState,
            placeholder = "비밀번호를 다시 입력하세요",
            label = "비밀번호 확인",
            isPassword = true,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
                keyboardType = KeyboardType.Password
            ),
            onKeyboardAction = {
                keyboardController?.hide()
                focusManager.clearFocus()
            },
        )

        Spacer(Modifier.weight(1f))

        ButtonPrimary(
            text = "회원가입",
            onClick = onRegisterClick,
            enabled = registerEnabled,
        )

        Spacer(Modifier.height(26.dp))
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    LETSSOPTTheme {
        RegisterScreen(
            emailState = rememberTextFieldState(),
            passwordState = rememberTextFieldState(),
            passwordCheckState = rememberTextFieldState(),
            onRegisterClick = {},
            registerEnabled = true,
        )
    }
}