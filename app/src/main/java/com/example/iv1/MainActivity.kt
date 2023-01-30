package com.example.iv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.example.iv1.ui.DrugViewModel
import com.example.iv1.ui.SetData
import com.example.iv1.ui.theme.IV1Theme

class MainActivity : ComponentActivity() {
    private val viewModel: DrugViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IV1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    SetData(viewModel = viewModel)
                }
            }
        }
    }
}