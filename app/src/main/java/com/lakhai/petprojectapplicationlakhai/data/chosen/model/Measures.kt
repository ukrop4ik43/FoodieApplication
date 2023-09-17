package com.lakhai.petprojectapplicationlakhai.data.chosen.model

import com.google.gson.annotations.SerializedName
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.Metric
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.Us


data class Measures (

    @SerializedName("us"     ) var us     : Us?     = Us(),
    @SerializedName("metric" ) var metric : Metric? = Metric()

)