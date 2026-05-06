package com.example.letssopt.presentation.auth.login

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
import androidx.compose.foundation.text.input.rememberTextFieldState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.common.extension.toast
import com.example.letssopt.core.common.util.HandleUiEffects
import com.example.letssopt.core.designsystem.component.ButtonPrimary
import com.example.letssopt.core.designsystem.component.TextFieldDefault
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.auth.component.LogoText

@Composable
fun LoginRoute(
    navigateToRegister: ()->Unit,
    navigateToMain: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
) {
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            LoginUiEffect.NavigateToRegister -> navigateToRegister()

            LoginUiEffect.NavigateToMain -> navigateToMain()

            is LoginUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    LoginScreen(
        emailState = viewModel.idState,
        passwordState = viewModel.passwordState,
        loginEnabled = viewModel.loginEnabled,
        onLoginClick = viewModel::onLoginClick,
        onRegisterClick = viewModel::onRegisterClick,
        modifier = modifier,
    )
}

@Composable
private fun LoginScreen(
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
                text = stringResource(R.string.login_btn),
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
                    text = stringResource(R.string.login_title),
                    modifier = Modifier.align(Alignment.Start),
                    color = LETSSOPTTheme.colors.white,
                    style = LETSSOPTTheme.typography.h2,
                )

                Spacer(Modifier.height(36.dp))

                TextFieldDefault(
                    state = emailState,
                    placeholder = stringResource(R.string.placeholder_id),
                    label = stringResource(R.string.label_id),
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
                    placeholder = stringResource(R.string.placeholder_password),
                    label = stringResource(R.string.label_password),
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
                text = stringResource(R.string.login_btn_register),
                modifier = Modifier
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = onRegisterClick,
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
