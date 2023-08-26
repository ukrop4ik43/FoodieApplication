package com.example.example

import com.google.gson.annotations.SerializedName


data class Us (

  @SerializedName("amount"    ) var amount    : Double?    = null,
  @SerializedName("unitShort" ) var unitShort : String? = null,
  @SerializedName("unitLong"  ) var unitLong  : String? = null

)