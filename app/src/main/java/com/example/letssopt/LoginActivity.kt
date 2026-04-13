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
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.foundation.text.input.clearText
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
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
    private var resultEmail = ""
    private var resultPassword = ""

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

            val loginEnabled by remember {
                derivedStateOf { emailState.text.isNotBlank() && passwordState.text.isNotBlank() }
            }

            LETSSOPTTheme {
                LoginScreen(
                    emailState = emailState,
                    passwordState = passwordState,
                    loginEnabled = loginEnabled,
                    onLoginClick = {
                        onLoginClick(
                            emailText = emailState.text.toString(),
                            passwordText = passwordState.text.toString(),
                        )
                    },
                    onRegisterClick = ::onRegisterClick,
                )
            }
        }
    }

    private fun onRegisterClick() {
        val intent = Intent(this, RegisterActivity::class.java)
        registerLauncher.launch(intent)
    }

    private enum class LoginValidationError(val message: String) {
        EMAIL_NOT_FOUND("존재하지 않는 이메일입니다"),
        PASSWORD_MISMATCH("비밀번호가 올바르지 않습니다"),
    }

    private fun validateLoginInputs(
        emailText: String,
        passwordText: String,
    ): LoginValidationError? {
        return when {
            emailText != resultEmail -> LoginValidationError.EMAIL_NOT_FOUND
            passwordText != resultPassword -> LoginValidationError.PASSWORD_MISMATCH
            else -> null
        }
    }

    private fun onLoginClick(
        emailText: String,
        passwordText: String,
    ) {
        val error = validateLoginInputs(emailText, passwordText)
        if (error != null) {
            Toast.makeText(this, error.message, Toast.LENGTH_SHORT).show()
            return
        }

        Toast.makeText(this, "로그인에 성공했습니다", Toast.LENGTH_SHORT).show()

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
    }
}

@Composable
fun LoginScreen(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    loginEnabled: Boolean,
    onLoginClick: () -> Unit,
    onRegisterClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val scrollState = rememberScrollState()

    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        bottomBar = {
            ButtonPrimary(
                text = "로그인",
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onLoginClick()
                },
                enabled = loginEnabled,
                modifier = Modifier
                    .navigationBarsPadding()
                    .padding(horizontal = 20.dp)
                    .padding(top = 8.dp, bottom = 26.dp)
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                modifier = Modifier.verticalScroll(scrollState),
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
            }

            Spacer(Modifier.weight(1f))

            Text(
                text = "아직 계정이 없으신가요?  회원가입",
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            emailState.clearText()
                            passwordState.clearText()
                            onRegisterClick()
                        },
                    )
                    .padding(top = 20.dp, bottom = 12.dp),
                color = LETSSOPTTheme.colors.textSecondary,
                style = LETSSOPTTheme.typography.caption
            )
        }
    }
}

@Preview
@Composable
private fun LoginScreenPreview() {
    LETSSOPTTheme {
        LoginScreen(
            emailState = rememberTextFieldState(),
            passwordState = rememberTextFieldState(),
            loginEnabled = true,
            onLoginClick = {},
            onRegisterClick = {},
        )
    }
}
