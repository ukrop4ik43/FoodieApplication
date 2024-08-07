package com.example.example

import com.google.gson.annotations.SerializedName
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.Ingredients


data class Steps (

    @SerializedName("number"      ) var number      : Int?                   = null,
    @SerializedName("step"        ) var step        : String?                = null,
    @SerializedName("ingredients" ) var ingredients : ArrayList<Ingredients> = arrayListOf(),

    )