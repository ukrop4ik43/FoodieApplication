package com.lakhai.petprojectapplicationlakhai.data.chosen.model

import com.google.gson.annotations.SerializedName


data class Us (

  @SerializedName("amount"    ) var amount    : Double?    = null,
  @SerializedName("unitShort" ) var unitShort : String? = null,
  @SerializedName("unitLong"  ) var unitLong  : String? = null

)