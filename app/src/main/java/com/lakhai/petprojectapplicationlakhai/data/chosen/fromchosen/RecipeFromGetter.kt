package com.lakhai.petprojectapplicationlakhai.data.chosen.fromchosen

import android.content.Context
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.FavoriteRecipe
import com.lakhai.petprojectapplicationlakhai.data.constants.ConstantsObject
import com.lakhai.petprojectapplicationlakhai.data.recipes.RecipesGetter
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.FavoriteModelListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RecipeFromGetter(val context: Context) {
    val logging= HttpLoggingInterceptor()
    fun getIngredientInstructions(listenerRecipe: FavoriteModelListener, id: String, i: Int){
        logging.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit: Retrofit = Retrofit.Builder().
        baseUrl(ConstantsObject.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).client(client).build()

        val  callRecipe: RecipesGetter.CallChosenModel =retrofit.create(RecipesGetter.CallChosenModel::class.java)

        val call: Call<FavoriteRecipe> =callRecipe.callRecipeInstruction(
            id,
            ConstantsObject.API_KEY,"false"
        )
        call.enqueue(object : Callback<FavoriteRecipe> {
            override fun onResponse(
                call: Call<FavoriteRecipe>,
                response: Response<FavoriteRecipe>
            ) {
                if(!response.isSuccessful){
                    listenerRecipe.didError("Error blabla:${response.message()}")
                }
                response.body()?.let { listenerRecipe.didFetch(it,response.message(),i) }
            }

            override fun onFailure(call: Call<FavoriteRecipe>, t: Throwable) {
                listenerRecipe.didError("Error:${t.message}")
            }

        })
    }
}