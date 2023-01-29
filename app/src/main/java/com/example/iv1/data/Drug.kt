package com.example.iv1.data

import java.lang.reflect.Constructor

data class Drug (
    var compatible_drugs: ArrayList<CompatibleDrugs> = ArrayList(),
    var drug_name: String = "",
    var id: Int = 0,
    var incompatible_drugs: ArrayList<String> = ArrayList(),
    var iv_fluid: ArrayList<IVFluid> = ArrayList(),
    var pH: Double = 0.0,
    var storage: String = ""
)
