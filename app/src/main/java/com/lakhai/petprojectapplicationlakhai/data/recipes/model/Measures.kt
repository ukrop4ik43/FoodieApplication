package com.example.example

import com.google.gson.annotations.SerializedName


data class Measures (

  @SerializedName("us"     ) var us     : Us?     = Us(),
  @SerializedName("metric" ) var metric : Metric? = Metric()

)