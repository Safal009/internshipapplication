package com.example.iv1.ui

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.iv1.data.DataState
import com.example.iv1.data.Drug
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class DrugViewModel: ViewModel() {
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)

    init {
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        response.value = DataState.Loading
        val ref = FirebaseDatabase.getInstance().getReference()
        val drugRef = ref.child("Drugs/0").child("incompatible_drugs")

        drugRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var drugList: ArrayList<String> = ArrayList()
                for(ds: DataSnapshot in snapshot.children) run {
                    val drug: String? = ds.value as String?
                    if (drug != null) {
                        drugList.add(drug)
                    }
                }
                response.value = DataState.Success(drugList)
            }

            override fun onCancelled(error: DatabaseError) {
                response.value = DataState.Failure(error.message)
            }

        })
    }
}