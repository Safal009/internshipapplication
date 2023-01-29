package com.example.iv1.data

data class Drug (
    var compatible_drugs: HashMap<String, Any> = HashMap(),
    var drug_name: String = "",
    var id: Int = 0,
    var incompatible_drugs: ArrayList<String> = ArrayList(),
    var iv_fluid: HashMap<String, Any> = HashMap(),
    var pH: String = "",
    var storage: String = ""
)
