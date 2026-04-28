package com.example.letssopt.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.presentation.main.navigation.rememberLETSSOPTNavigator

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navigator = rememberLETSSOPTNavigator()
            val startDestination by viewModel.startDestination.collectAsStateWithLifecycle()

            LETSSOPTTheme {
                startDestination?.let { dest ->
                    MainScreen(
                        startDestination = dest,
                        navigator = navigator,
                    )
                }
            }
        }
    }
}
