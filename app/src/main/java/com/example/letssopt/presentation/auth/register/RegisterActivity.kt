package com.example.letssopt.presentation.auth.register

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
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
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
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
import com.example.letssopt.core.designsystem.component.ButtonPrimary
import com.example.letssopt.core.designsystem.component.TextFieldDefault
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.util.HandleUiEffects
import com.example.letssopt.presentation.auth.component.LogoText

class RegisterActivity : ComponentActivity() {
    private val viewModel by viewModels<RegisterViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                val context = LocalContext.current

                HandleUiEffects(viewModel.uiEffect) { effect ->
                    when (effect) {
                        RegisterUiEffect.BackToLogin -> finish()

                        is RegisterUiEffect.ShowToast -> Toast.makeText(
                            context,
                            effect.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }


                RegisterScreen(
                    emailState = viewModel.emailState,
                    passwordState = viewModel.passwordState,
                    passwordConfirmState = viewModel.passwordConfirmState,
                    registerEnabled = viewModel.registerEnabled,
                    onRegisterClick = viewModel::onRegisterClick,
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(
    emailState: TextFieldState,
    passwordState: TextFieldState,
    passwordConfirmState: TextFieldState,
    registerEnabled: Boolean,
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
                text = "회원가입",
                onClick = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    onRegisterClick()
                },
                enabled = registerEnabled,
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
                .padding(horizontal = 20.dp)
                .verticalScroll(scrollState),
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
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
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
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Password
                ),
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                },
            )

            Spacer(Modifier.height(18.dp))

            TextFieldDefault(
                state = passwordConfirmState,
                placeholder = "비밀번호를 다시 입력하세요",
                label = "비밀번호 확인",
                isPassword = true,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done, keyboardType = KeyboardType.Password
                ),
                onKeyboardAction = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                },
            )
        }
    }
}

@Preview
@Composable
private fun RegisterScreenPreview() {
    LETSSOPTTheme {
        RegisterScreen(
            emailState = rememberTextFieldState(),
            passwordState = rememberTextFieldState(),
            passwordConfirmState = rememberTextFieldState(),
            registerEnabled = true,
            onRegisterClick = {}
        )
    }
}
