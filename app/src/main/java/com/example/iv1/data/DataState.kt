package com.example.iv1.data

import com.example.iv1.data.Drug

sealed class DataState {
    class Success(val data: ArrayList<String>): DataState()
    class Failure(val message: String): DataState()
    object Loading: DataState()
    object Empty: DataState()
}
