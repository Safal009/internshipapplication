package com.example.iv1.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug

@Composable
fun StartCheck() {
    val toCheck: ArrayList<Pair<Drug, Drug>> = ArrayList()
    for(i in 0 until tempList.size - 1) {
        for(j in i+1 until tempList.size) {
            if(i != j) {
                toCheck.add(Pair(tempList[i], tempList[j]))
            }
        }
    }
    LazyColumn {
        items(toCheck) {drugPair ->
            Check(drugPair)
        }
    }
}

@Composable
fun Check(checkWith: Pair<Drug, Drug>) {
    val res: ArrayList<String> = ArrayList()
    var temp = false
    for(i in 0 until checkWith.first.incompatible_drugs.size) {
        res.add(checkWith.first.incompatible_drugs[i].lowercase())
    }
    for(i in 0 until res.size) {
        if(checkWith.first.incompatible_drugs[i].lowercase() == checkWith.second.drug_name.lowercase()) {
            temp = true
            break
        } else {
            temp = false
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = checkWith.first.drug_name + " -- " + checkWith.second.drug_name + " --> " + temp, fontSize = MaterialTheme.typography.h5.fontSize)
        }
    }
}
