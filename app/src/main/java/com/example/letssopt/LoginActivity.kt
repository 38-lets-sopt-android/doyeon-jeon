package com.example.letssopt

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
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

const val EMAIL_KEY = "emailKey"
const val PASSWORD_KEY = "passwordKey"

class LoginActivity : ComponentActivity() {
    private var resultEmail by mutableStateOf("")
    private var resultPassword by mutableStateOf("")

    private val registerLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            resultEmail = result.data?.getStringExtra(EMAIL_KEY) ?: ""
            resultPassword = result.data?.getStringExtra(PASSWORD_KEY) ?: ""
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            val emailState = rememberTextFieldState()
            val passwordState = rememberTextFieldState()

            val loginEnabled = emailState.text.isNotBlank() && passwordState.text.isNotBlank()

            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        emailState = emailState,
                        passwordState = passwordState,
                        onRegisterClick = {
                            onRegisterClick(
                                emailState = emailState,
                                passwordState = passwordState,
                            )
                        },
                        onLoginClick = {
                            onLoginClick(
                                emailText = emailState.text.toString(),
                                passwordText = passwordState.text.toString(),
                            )
                        },
                        loginEnabled = loginEnabled,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }
        }
    }

    private fun onRegisterClick(
        emailState: TextFieldState,
        passwordState: TextFieldState,
    ) {
        emailState.clearText()
        passwordState.clearText()

        val intent = Intent(this, RegisterActivity::class.java)
        registerLauncher.launch(intent)
    }

    private fun onLoginClick(
        emailText: String,
        passwordText: String,
    ) {
        if (emailText == resultEmail && passwordText == resultPassword) {
            Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            this.startActivity(intent)
        } else {
            Toast.makeText(this, "이메일 또는 비밀번호가 올바르지 않습니다", Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable
fun LoginScreen(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    onRegisterClick: () -> Unit,
    onLoginClick: () -> Unit,
    loginEnabled: Boolean,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
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
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Next,
                keyboardType = KeyboardType.Email
            ),
            onKeyboardAction = {
                focusManager.moveFocus(FocusDirection.Next)
            },
        )

        Spacer(Modifier.height(36.dp))

        TextFieldDefault(
            state = passwordState,
            placeholder = "비밀번호를 입력하세요",
            label = "비밀번호",
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

        Text(
            text = "아직 계정이 없으신가요?  회원가입",
            modifier = Modifier
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onRegisterClick,
                )
                .padding(vertical = 20.dp),
            color = LETSSOPTTheme.colors.textSecondary,
            style = LETSSOPTTheme.typography.caption
        )

        ButtonPrimary(
            text = "로그인",
            onClick = onLoginClick,
            enabled = loginEnabled,
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
            onRegisterClick = {},
            onLoginClick = {},
            loginEnabled = true,
        )
    }
}