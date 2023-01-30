package com.example.iv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.iv1.data.DataState
import com.example.iv1.data.Drug

val tempList: ArrayList<Drug> = ArrayList()

@Composable
fun SetData(viewModel: DrugViewModel) {
    when(val result = viewModel.response.value) {
        is DataState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            ShowDrugList(result.data)
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
fun ShowDrugList(drugs: ArrayList<Drug>) {
    LazyColumn {
        items(drugs) {drug ->
            ListItem(drug)

        }
    }
}


@Composable
fun ListItem(drug: Drug) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = drug.drug_name, fontSize = MaterialTheme.typography.h5.fontSize)
            OutlinedButton(onClick = { selectDrug(drug) }) {
                Text(text = "Add")
            }
        }
    }
}

fun selectDrug(drug: Drug) {
    tempList.add(drug)
    println(tempList)
}

