package com.example.letssopt.core.common.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow

@Composable
fun <T> HandleUiEffects(
    uiEffectFlow: Flow<T>,
    lifecycleState: Lifecycle.State = Lifecycle.State.STARTED,
    onSideEffect: (T) -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner, uiEffectFlow) {
        lifecycleOwner.lifecycle.repeatOnLifecycle(lifecycleState) {
            uiEffectFlow.collect { effect ->
                onSideEffect(effect)
            }
        }
    }
}
