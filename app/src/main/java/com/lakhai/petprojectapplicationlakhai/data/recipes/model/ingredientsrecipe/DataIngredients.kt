package com.lakhai.petprojectapplicationlakhai.data.recipes.model.ingredientsrecipe


// import com.fasterxml.jackson.databind.ObjectMapper; // version 2.11.1
// import com.fasterxml.jackson.annotation.JsonProperty; // version 2.11.1
/* ObjectMapper om = new ObjectMapper();
Root[] root = om.readValue(myJsonString, Root[].class); */
class MissedIngredient {
    var id = 0
    var amount = 0.0
    var unit: String? = null
    var unitLong: String? = null
    var unitShort: String? = null
    var aisle: String? = null
    var name: String? = null
    var original: String? = null
    var originalName: String? = null
    var meta: ArrayList<String>? = null
    var image: String? = null
    var extendedName: String? = null
}

class GettedIngredientReceipt {
    var id = 0
    var title: String? = null
    var image: String? = null
    var imageType: String? = null
    var usedIngredientCount = 0
    var missedIngredientCount = 0
    var missedIngredients: ArrayList<MissedIngredient>? = null
    var usedIngredients: ArrayList<UsedIngredient>? = null
    var unusedIngredients: ArrayList<Any>? = null
    var likes = 0
}

class UsedIngredient {
    var id = 0
    var amount = 0.0
    var unit: String? = null
    var unitLong: String? = null
    var unitShort: String? = null
    var aisle: String? = null
    var name: String? = null
    var original: String? = null
    var originalName: String? = null
    var meta: ArrayList<String>? = null
    var image: String? = null
}

