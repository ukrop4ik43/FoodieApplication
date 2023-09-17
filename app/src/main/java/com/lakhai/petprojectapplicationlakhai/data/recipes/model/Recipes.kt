package com.example.example

import com.google.gson.annotations.SerializedName
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.AnalyzedInstructions
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.ExtendedIngredients


data class Recipes (

    @SerializedName("vegetarian"               ) var vegetarian               : Boolean?                        = null,
    @SerializedName("vegan"                    ) var vegan                    : Boolean?                        = null,
    @SerializedName("glutenFree"               ) var glutenFree               : Boolean?                        = null,
    @SerializedName("dairyFree"                ) var dairyFree                : Boolean?                        = null,
    @SerializedName("veryHealthy"              ) var veryHealthy              : Boolean?                        = null,
    @SerializedName("cheap"                    ) var cheap                    : Boolean?                        = null,
    @SerializedName("veryPopular"              ) var veryPopular              : Boolean?                        = null,
    @SerializedName("sustainable"              ) var sustainable              : Boolean?                        = null,
    @SerializedName("lowFodmap"                ) var lowFodmap                : Boolean?                        = null,
    @SerializedName("weightWatcherSmartPoints" ) var weightWatcherSmartPoints : Int?                            = null,
    @SerializedName("gaps"                     ) var gaps                     : String?                         = null,
    @SerializedName("preparationMinutes"       ) var preparationMinutes       : Int?                            = null,
    @SerializedName("cookingMinutes"           ) var cookingMinutes           : Int?                            = null,
    @SerializedName("aggregateLikes"           ) var aggregateLikes           : Int?                            = null,
    @SerializedName("healthScore"              ) var healthScore              : Int?                            = null,
    @SerializedName("creditsText"              ) var creditsText              : String?                         = null,
    @SerializedName("sourceName"               ) var sourceName               : String?                         = null,
    @SerializedName("pricePerServing"          ) var pricePerServing          : Double?                         = null,
    @SerializedName("extendedIngredients"      ) var extendedIngredients      : ArrayList<ExtendedIngredients>  = arrayListOf(),
    @SerializedName("id"                       ) var id                       : Int?                            = null,
    @SerializedName("title"                    ) var title                    : String?                         = null,
    @SerializedName("readyInMinutes"           ) var readyInMinutes           : Int?                            = null,
    @SerializedName("servings"                 ) var servings                 : Int?                            = null,
    @SerializedName("sourceUrl"                ) var sourceUrl                : String?                         = null,
    @SerializedName("image"                    ) var image                    : String?                         = null,
    @SerializedName("imageType"                ) var imageType                : String?                         = null,
    @SerializedName("summary"                  ) var summary                  : String?                         = null,
    @SerializedName("cuisines"                 ) var cuisines                 : ArrayList<String>               = arrayListOf(),
    @SerializedName("dishTypes"                ) var dishTypes                : ArrayList<String>               = arrayListOf(),
    @SerializedName("diets"                    ) var diets                    : ArrayList<String>               = arrayListOf(),
    @SerializedName("occasions"                ) var occasions                : ArrayList<String>               = arrayListOf(),
    @SerializedName("instructions"             ) var instructions             : String?                         = null,
    @SerializedName("analyzedInstructions"     ) var analyzedInstructions     : ArrayList<AnalyzedInstructions> = arrayListOf(),
    @SerializedName("originalId"               ) var originalId               : String?                         = null,
    @SerializedName("spoonacularSourceUrl"     ) var spoonacularSourceUrl     : String?                         = null

)