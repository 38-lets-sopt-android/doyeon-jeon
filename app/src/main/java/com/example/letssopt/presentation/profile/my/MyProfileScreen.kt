package com.example.letssopt.presentation.profile.my

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.core.base.Async
import com.example.letssopt.core.common.extension.toast
import com.example.letssopt.core.common.util.HandleUiEffects
import com.example.letssopt.core.designsystem.component.ButtonPrimary
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.profile.component.MyInfoList
import com.example.letssopt.presentation.profile.model.MyInfoUiModel

@Composable
fun MyProfileRoute(
    navigateToProfileList: () -> Unit,
    navigateToLogin: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: MyProfileViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            MyProfileUiEffect.NavigateToLogin -> navigateToLogin()
            MyProfileUiEffect.NavigateToProfileList -> navigateToProfileList()
            is MyProfileUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    when (val myInfos = uiState.myInfos) {
        Async.Empty, Async.Init -> Unit
        Async.Loading -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }

        is Async.Success<List<MyInfoUiModel>> -> MyProfileScreen(
            myInfos = myInfos.data,
            onClick = viewModel::onNavigateToProfileList,
            modifier = modifier,
        )
    }
}

@Composable
fun MyProfileScreen(
    myInfos: List<MyInfoUiModel>,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(70.dp))

        Text(
            text = "프로필",
            color = LETSSOPTTheme.colors.white,
            style = LETSSOPTTheme.typography.h3,
        )

        Spacer(Modifier.height(68.dp))

        MyInfoList(myInfos = myInfos)

        Spacer(Modifier.height(30.dp))

        ButtonPrimary(
            text = "다른 유저들 보러가기",
            onClick = onClick,
            enabled = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(30.dp))
    }
}