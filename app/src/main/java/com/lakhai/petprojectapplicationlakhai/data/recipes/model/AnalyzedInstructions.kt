package com.example.example

import com.google.gson.annotations.SerializedName
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.Steps


data class AnalyzedInstructions (

  @SerializedName("name"  ) var name  : String?          = null,
  @SerializedName("steps" ) var steps : ArrayList<Steps> = arrayListOf()

)