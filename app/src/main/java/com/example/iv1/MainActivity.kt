package com.example.iv1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.iv1.data.DataState
import com.example.iv1.data.Drug
import com.example.iv1.ui.DrugViewModel
import com.example.iv1.ui.theme.IV1Theme

class MainActivity : ComponentActivity() {
    val viewModel: DrugViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IV1Theme {
                // A surface container using the 'background' color from the theme
                Column {
                    SetData(viewModel)
                }
            }
        }
    }
    @Composable
    private fun SetData(viewModel: DrugViewModel) {
        when(val result = viewModel.response.value) {
            is DataState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            is DataState.Success -> {
                ShowLazyList(result.data)
            }
            is DataState.Failure -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = result.message, fontSize = MaterialTheme.typography.h5.fontSize)
                }
            }
            else -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = "Error fetching data !!", fontSize = MaterialTheme.typography.h5.fontSize)
                }
            }
        }

    }

    @Composable
    fun ShowLazyList(drugs: ArrayList<Drug>) {
        LazyColumn {
            items(drugs) {drug ->
                CardItem(drug)
            }
        }
    }

    @Composable
    fun CardItem(drug: Drug) {
        Card(modifier = Modifier.fillMaxWidth().height(150.dp).padding(10.dp)) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize, modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center)
            }
        }
    }
}