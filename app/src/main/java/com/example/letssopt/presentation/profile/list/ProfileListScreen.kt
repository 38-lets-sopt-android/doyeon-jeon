package com.example.letssopt.presentation.profile.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.base.Async
import com.example.letssopt.core.common.extension.toast
import com.example.letssopt.core.common.util.HandleUiEffects
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.domain.model.UserModel
import com.example.letssopt.presentation.profile.component.UserInfoList

@Composable
fun ProfileListRoute(
    modifier: Modifier = Modifier,
    viewModel: ProfileListViewModel = viewModel(factory = ProfileListViewModelFactory()),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            is ProfileListUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    when (val usersInfo = uiState.userInfos) {
        Async.Empty, Async.Init -> Unit
        Async.Loading -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

        is Async.Success<List<UserModel>> -> ProfileListScreen(
            usersInfo = usersInfo.data,
            modifier = modifier,
        )
    }
}

@Composable
private fun ProfileListScreen(
    usersInfo: List<UserModel>,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Spacer(Modifier.height(70.dp))

        Text(
            text = stringResource(R.string.profilelist_title),
            modifier = Modifier.padding(start = 27.dp),
            color = LETSSOPTTheme.colors.white,
            style = LETSSOPTTheme.typography.h3,
        )

        Spacer(Modifier.height(142.dp))

        UserInfoList(
            usersInfo = usersInfo,
        )
    }
}
