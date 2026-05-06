package com.example.letssopt.presentation.auth.register

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
fun RegisterRoute(
    popBackStack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RegisterViewModel = viewModel(factory = RegisterViewModelFactory()),
) {
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            RegisterUiEffect.BackToLogin -> popBackStack()

            is RegisterUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    RegisterScreen(
        idState = viewModel.idState,
        passwordState = viewModel.passwordState,
        passwordConfirmState = viewModel.passwordConfirmState,
        nameState = viewModel.nameState,
        emailState = viewModel.emailState,
        ageState = viewModel.ageState,
        partState = viewModel.partState,
        registerEnabled = viewModel.registerEnabled,
        onRegisterClick = viewModel::onRegisterClick,
        modifier = modifier,
    )
}

@Composable
private fun RegisterScreen(
    idState: TextFieldState,
    passwordState: TextFieldState,
    passwordConfirmState: TextFieldState,
    nameState: TextFieldState,
    emailState: TextFieldState,
    ageState: TextFieldState,
    partState: TextFieldState,
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
                text = stringResource(R.string.register_btn),
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
                text = stringResource(R.string.register_title),
                modifier = Modifier.align(Alignment.Start),
                color = LETSSOPTTheme.colors.white,
                style = LETSSOPTTheme.typography.h2,
            )

            Spacer(Modifier.height(36.dp))

            TextFieldDefault(
                state = idState,
                placeholder = stringResource(R.string.placeholder_id),
                label = stringResource(R.string.label_id),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                },
            )

            Spacer(Modifier.height(18.dp))

            TextFieldDefault(
                state = passwordState,
                placeholder = stringResource(R.string.placeholder_password),
                label = stringResource(R.string.label_password),
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
                placeholder = stringResource(R.string.register_placeholder_passwordconfirm),
                label = stringResource(R.string.register_label_passwordconfirm),
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
                state = nameState,
                placeholder = stringResource(R.string.register_placeholder_name),
                label = stringResource(R.string.register_label_name),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                },
            )

            Spacer(Modifier.height(18.dp))

            TextFieldDefault(
                state = emailState,
                placeholder = stringResource(R.string.register_placeholder_email),
                label = stringResource(R.string.register_label_email),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Email
                ),
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                },
            )

            Spacer(Modifier.height(18.dp))

            TextFieldDefault(
                state = ageState,
                placeholder = stringResource(R.string.register_placeholder_age),
                label = stringResource(R.string.register_label_age),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next, keyboardType = KeyboardType.Number
                ),
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                },
            )

            Spacer(Modifier.height(18.dp))

            TextFieldDefault(
                state = partState,
                placeholder = stringResource(R.string.register_placeholder_part),
                label = stringResource(R.string.register_label_part),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
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
            idState = rememberTextFieldState(),
            passwordState = rememberTextFieldState(),
            passwordConfirmState = rememberTextFieldState(),
            emailState = rememberTextFieldState(),
            nameState = rememberTextFieldState(),
            ageState = rememberTextFieldState(),
            partState = rememberTextFieldState(),
            registerEnabled = true,
            onRegisterClick = {},
        )
    }
}
