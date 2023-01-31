package com.example.iv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StartScreen(
    onIRCalcButtonClicked: () -> Unit,
    onCompatibilityCheckButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment =Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedButton(onClick = { onCompatibilityCheckButtonClicked() }) {
            Text(text = "Check Compatibility")
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        OutlinedButton(onClick = { onIRCalcButtonClicked() }) {
            Text(text = "Infusion Rate Calculator")
        }
    }
}