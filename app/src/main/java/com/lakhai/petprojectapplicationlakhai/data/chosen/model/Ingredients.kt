package com.lakhai.petprojectapplicationlakhai.data.chosen.model

import com.google.gson.annotations.SerializedName


data class Ingredients (

  @SerializedName("id"            ) var id            : Int?    = null,
  @SerializedName("name"          ) var name          : String? = null,
  @SerializedName("localizedName" ) var localizedName : String? = null,
  @SerializedName("image"         ) var image         : String? = null

)