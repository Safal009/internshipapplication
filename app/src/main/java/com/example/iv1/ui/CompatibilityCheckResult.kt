package com.example.iv1.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.iv1.data.Drug

@OptIn(ExperimentalFoundationApi::class)
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
        toCheck.forEach { pair ->
            stickyHeader {
                Text(
                    text = pair.first.drug_name.trim() + " -- " + pair.second.drug_name.trim(),
                    color = Color.White,
                    modifier = Modifier
                        .background(Color.Gray)
                        .padding(5.dp)
                        .fillMaxWidth()
                )
            }
            items(listOf(pair)) {drugPair ->
                DisplayCheck(drugPair)
            }
        }
    }
}

@Composable
fun DisplayCheck(toCheck: Pair<Drug, Drug>) {
    val res: ArrayList<String> = ArrayList()
    for(i in 0 until toCheck.first.incompatible_drugs.size) {
        res.add(toCheck.first.incompatible_drugs[i].lowercase().trim())
    }
    val temp = res.indexOf(toCheck.second.drug_name.lowercase().trim())
//    println(toCheck.second.drug_name.lowercase().trim())
//    println(res)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
    ) {
        Row(modifier = Modifier.fillMaxSize()) {
            Text(text = toCheck.first.drug_name.trim() + " -- " + toCheck.second.drug_name.trim() + " --> " + temp, fontSize = MaterialTheme.typography.h5.fontSize)
        }
    }
}
