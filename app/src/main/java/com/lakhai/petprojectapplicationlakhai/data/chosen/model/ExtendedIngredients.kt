package com.lakhai.petprojectapplicationlakhai.data.chosen.model

import com.google.gson.annotations.SerializedName


data class ExtendedIngredients (

  @SerializedName("id"           ) var id           : Int?              = null,
  @SerializedName("aisle"        ) var aisle        : String?           = null,
  @SerializedName("image"        ) var image        : String?           = null,
  @SerializedName("consistency"  ) var consistency  : String?           = null,
  @SerializedName("name"         ) var name         : String?           = null,
  @SerializedName("nameClean"    ) var nameClean    : String?           = null,
  @SerializedName("original"     ) var original     : String?           = null,
  @SerializedName("originalName" ) var originalName : String?           = null,
  @SerializedName("amount"       ) var amount       : Double?              = null,
  @SerializedName("unit"         ) var unit         : String?           = null,
  @SerializedName("meta"         ) var meta         : ArrayList<String> = arrayListOf(),
  @SerializedName("measures"     ) var measures     : Measures?         = Measures()

)