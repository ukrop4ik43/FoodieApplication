package com.lakhai.petprojectapplicationlakhai.ui

import android.annotation.SuppressLint
import android.content.Intent
import com.lakhai.petprojectapplicationlakhai.data.webview.WebViewCustomClient


import android.os.Bundle
import android.util.Log
import android.webkit.WebSettings
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.text.toLowerCase
import com.bumptech.glide.Glide
import com.example.example.RandomRecipeClass
import com.example.example.Recipes
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.recipes.RecipesGetter
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.RandomRecipeListener
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.toast.ToastForRandomRecipe
import com.lakhai.petprojectapplicationlakhai.data.webview.WebViewSettings
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityRandomrecipeBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.util.Locale

class RandomrecipeActivity : AppCompatActivity() {
    var listOfRecipes: ArrayList<Recipes>? = null
    private var _binding: ActivityRandomrecipeBinding? = null
    val binding get() = _binding!!
    val managerForRandomRequest: RecipesGetter by inject()
    val toastImpl: ToastForRandomRecipe by inject { parametersOf(this@RandomrecipeActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRandomrecipeBinding.inflate(layoutInflater)

        binding.backButton.setOnClickListener {
            val activityEndIntent = Intent(this@RandomrecipeActivity, MenuActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }
        randomRecipeResponceListener.activity = this@RandomrecipeActivity
        managerForRandomRequest.getRandomRecipes(randomRecipeResponceListener)
        setContentView(R.layout.load_receipt)
    }

    internal object randomRecipeResponceListener : RandomRecipeListener {
        var activity: RandomrecipeActivity? = null

        @SuppressLint("SetJavaScriptEnabled")
        override fun didFetch(responce: RandomRecipeClass, message: String) {
            Log.d("ErrorsOfRandom", "good")
            activity?.listOfRecipes = responce.recipes
            activity?.let {
                activity?.binding?.ivRecipe?.let { it1 ->
                    Glide.with(it)
                        .load((activity?.listOfRecipes as ArrayList<Recipes>)[0].image)
                        .fitCenter()
                        .circleCrop()
                        .into(it1)

                }
                val ingredients = responce.recipes[0].extendedIngredients
                var recipeIngredients = "Ingredients:\n"
                for (index in ingredients.indices) {
                    recipeIngredients += "- ${ingredients[index].original}\n"
                }
                val title = responce.recipes[0].title?.replace("\\<[^>]*>", "")

                val instructions = responce.recipes[0].instructions?.replace("\\<[^>]*>", "")
                val ingredientsAndInstructions = recipeIngredients + "\n" + "\n" + instructions
                activity?.binding?.deviliousRecipeTv?.text = title
                activity?.binding?.recipeTv?.text = ingredientsAndInstructions
                WebViewSettings().setWebView(activity!!, title.toString())


            }
        }

        override fun didError(message: String) {
            Log.d("ErrorsOfRandom", message)
            activity?.toastImpl?.toastSetter(message)
        }

    }

    override fun onBackPressed() {
        val activityEndIntent = Intent(this@RandomrecipeActivity, MenuActivity::class.java)
        startActivity(activityEndIntent)
        finish()
    }
}