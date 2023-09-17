package com.lakhai.petprojectapplicationlakhai.data.recipes

import android.content.Context
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.FavoriteRecipe
import com.example.example.RandomRecipeClass
import com.lakhai.petprojectapplicationlakhai.data.constants.ConstantsObject
import com.lakhai.petprojectapplicationlakhai.data.recipes.model.ingredientsrecipe.GettedIngredientReceipt
import com.lakhai.petprojectapplicationlakhai.data.recipes.model.instructions.RootInstructions
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.IngredientInstructionsListener
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.IngredientRecipeListener
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.RandomRecipeListener
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


class RecipesGetter(val context: Context) {
    val logging= HttpLoggingInterceptor()


    fun getIngredientRecipes(listenerRecipe: IngredientRecipeListener,ingredients:String){
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit:Retrofit=Retrofit.Builder().
        baseUrl(ConstantsObject.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).client(client).build()

        val  callRecipe:CallIngredientsRecipe=retrofit.create(CallIngredientsRecipe::class.java)

        val call: Call<ArrayList<GettedIngredientReceipt>> =callRecipe.callRecipe(
            ingredients,
            ConstantsObject.API_KEY,
            "4"
        )
        call.enqueue(object :Callback<ArrayList<GettedIngredientReceipt>>{
            override fun onResponse(
                call: Call<ArrayList<GettedIngredientReceipt>>,
                response: Response<ArrayList<GettedIngredientReceipt>>
            ) {
                if(!response.isSuccessful){
                    listenerRecipe.didError("Error blabla:${response.message()}")
                }
                response.body()?.let { listenerRecipe.didFetch(it,response.message()) }
            }

            override fun onFailure(call: Call<ArrayList<GettedIngredientReceipt>>, t: Throwable) {
                listenerRecipe.didError("Error:${t.message}")
            }

        })
    }

    fun getIngredientInstructions(listenerRecipe: IngredientInstructionsListener, id:String,title:String){
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val retrofit:Retrofit=Retrofit.Builder().
        baseUrl(ConstantsObject.BASE_URL).
        addConverterFactory(GsonConverterFactory.create()).client(client).build()

        val  callRecipe:CallIngredientsInstructions=retrofit.create(CallIngredientsInstructions::class.java)

        val call: Call<RootInstructions> =callRecipe.callRecipeInstruction(
            id,
            ConstantsObject.API_KEY,"false"
        )
        call.enqueue(object :Callback<RootInstructions>{
            override fun onResponse(
                call: Call<RootInstructions>,
                response: Response<RootInstructions>
            ) {
                if(!response.isSuccessful){
                    listenerRecipe.didError("Error blabla:${response.message()}")
                }
                response.body()?.let { listenerRecipe.didFetch(it,response.message(),title) }
            }

            override fun onFailure(call: Call<RootInstructions>, t: Throwable) {
                listenerRecipe.didError("Error:${t.message}")
            }

        })
    }



    fun getRandomRecipes(listenerRandomRecipe: RandomRecipeListener){
        val retrofit:Retrofit=Retrofit.Builder().baseUrl(ConstantsObject.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

        val  callRandomRecipe:CallRandomRecipe=retrofit.create(CallRandomRecipe::class.java)
        val call: Call<RandomRecipeClass> =callRandomRecipe.callRandomRecipe(ConstantsObject.API_KEY,"10")
        call.enqueue(object :Callback<RandomRecipeClass>{
            override fun onResponse(
                call: Call<RandomRecipeClass>,
                response: Response<RandomRecipeClass>
            ) {
                if(!response.isSuccessful){
                    listenerRandomRecipe.didError("Error:${response.message()}")
                }
                response.body()?.let { listenerRandomRecipe.didFetch(it,response.message()) }
            }

            override fun onFailure(call: Call<RandomRecipeClass>, t: Throwable) {
                listenerRandomRecipe.didError("Error:${t.message}")
            }

        })
    }

    interface CallRandomRecipe{
        @GET("recipes/random")
       fun callRandomRecipe(
            @Query("apiKey") apiKey: String,
            @Query("number") randomNumber: String,
        ): Call<RandomRecipeClass>
    }

    interface CallIngredientsRecipe{
        @GET("recipes/findByIngredients")
        fun callRecipe(
            @Query("ingredients") ingredients:String,
            @Query("apiKey") apiKey: String,
            @Query("number") randomNumber: String

        ): Call<ArrayList<GettedIngredientReceipt>>
    }

    interface CallIngredientsInstructions{
        @GET("recipes/{id}/information")
        fun callRecipeInstruction(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String,
            @Query("includeNutrition") g: String,
        ): Call<RootInstructions>
    }

    interface CallChosenModel{
        @GET("recipes/{id}/information")
        fun callRecipeInstruction(
            @Path("id") id: String,
            @Query("apiKey") apiKey: String,
            @Query("includeNutrition") g: String,
        ): Call<FavoriteRecipe>
    }
}