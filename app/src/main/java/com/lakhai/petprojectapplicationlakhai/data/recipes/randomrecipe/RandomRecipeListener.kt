package com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe

import com.lakhai.petprojectapplicationlakhai.data.chosen.model.FavoriteRecipe
import com.example.example.RandomRecipeClass
import com.lakhai.petprojectapplicationlakhai.data.recipes.model.ingredientsrecipe.GettedIngredientReceipt
import com.lakhai.petprojectapplicationlakhai.data.recipes.model.instructions.RootInstructions

interface RandomRecipeListener {
    fun didFetch(responce:RandomRecipeClass,message:String)
    fun didError(message: String)
}
interface IngredientRecipeListener {
    fun didFetch(responce: ArrayList<GettedIngredientReceipt>, message:String)
    fun didError(message: String)
}
interface IngredientInstructionsListener {
    fun didFetch(responce: RootInstructions, message: String, title: String)
    fun didError(message: String)
}

interface FavoriteModelListener {
    fun didFetch(responce: FavoriteRecipe, message: String, i: Int)
    fun didError(message: String)
}