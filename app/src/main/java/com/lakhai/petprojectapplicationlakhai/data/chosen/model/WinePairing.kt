package com.lakhai.petprojectapplicationlakhai.data.chosen.model

import com.google.gson.annotations.SerializedName


data class WinePairing (

  @SerializedName("pairedWines"    ) var pairedWines    : ArrayList<String> = arrayListOf(),
  @SerializedName("pairingText"    ) var pairingText    : String?           = null,
  @SerializedName("productMatches" ) var productMatches : ArrayList<FavoriteRecipe> = arrayListOf()

)