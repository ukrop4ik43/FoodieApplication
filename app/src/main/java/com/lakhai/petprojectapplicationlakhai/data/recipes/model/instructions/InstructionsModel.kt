package com.lakhai.petprojectapplicationlakhai.data.recipes.model.instructions


// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root root = om.readValue(myJsonString, Root.class); */
class ExtendedIngredient {
    var id = 0
    var aisle: String? = null
    var image: String? = null
    var consistency: String? = null
    var name: String? = null
    var nameClean: String? = null
    var original: String? = null
    var originalName: String? = null
    var amount = 0.0
    var unit: String? = null
    var meta: ArrayList<String>? = null
    var measures: Measures? = null
}

class Measures {
    var us: Us? = null
    var metric: Metric? = null
}

class Metric {
    var amount = 0.0
    var unitShort: String? = null
    var unitLong: String? = null
}

class RootInstructions {
    var vegetarian = false
    var vegan = false
    var glutenFree = false
    var dairyFree = false
    var veryHealthy = false
    var cheap = false
    var veryPopular = false
    var sustainable = false
    var lowFodmap = false
    var weightWatcherSmartPoints = 0
    var gaps: String? = null
    var preparationMinutes = 0
    var cookingMinutes = 0
    var aggregateLikes = 0
    var healthScore = 0
    var creditsText: String? = null
    var license: String? = null
    var sourceName: String? = null
    var pricePerServing = 0.0
    var extendedIngredients: ArrayList<ExtendedIngredient>? = null
    var id = 0
    var title: String? = null
    var readyInMinutes = 0
    var servings = 0
    var sourceUrl: String? = null
    var image: String? = null
    var imageType: String? = null
    var summary: String? = null
    var cuisines: ArrayList<Any>? = null
    var dishTypes: ArrayList<String>? = null
    var diets: ArrayList<Any>? = null
    var occasions: ArrayList<Any>? = null
    var winePairing: WinePairing? = null
    var instructions: String? = null
    var analyzedInstructions: ArrayList<Any>? = null
    var originalId: Any? = null
    var spoonacularSourceUrl: String? = null
}

class Us {
    var amount = 0.0
    var unitShort: String? = null
    var unitLong: String? = null
}

class WinePairing {
    var pairedWines: ArrayList<Any>? = null
    var pairingText: String? = null
    var productMatches: ArrayList<Any>? = null
}

