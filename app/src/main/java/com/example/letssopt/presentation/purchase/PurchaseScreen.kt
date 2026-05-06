package com.example.letssopt.presentation.purchase

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.common.extension.toast
import com.example.letssopt.core.common.util.HandleUiEffects
import com.example.letssopt.domain.model.ContentModel
import com.example.letssopt.presentation.common.component.ContentGridSection

@Composable
fun PurchaseRoute(
    modifier: Modifier = Modifier,
    viewModel: PurchaseViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    HandleUiEffects(viewModel.uiEffect) { effect ->
        when (effect) {
            is PurchaseUiEffect.ShowToast -> context.toast(effect.message)
        }
    }

    PurchaseScreen(
        purchaseContents = uiState.purchaseContents,
        onSaveClick = viewModel::onSave,
        modifier = modifier,
    )
}

@Composable
private fun PurchaseScreen(
    purchaseContents: List<ContentModel>,
    onSaveClick: (ContentModel) -> Unit,
    modifier: Modifier = Modifier,
) {
    ContentGridSection(
        title = stringResource(R.string.purchase_title),
        contents = purchaseContents,
        onSaveClick = onSaveClick,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp)
            .padding(top = 70.dp),
    )
}
