package com.lakhai.petprojectapplicationlakhai.ui

import android.annotation.SuppressLint
import android.content.Intent


import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.example.RandomRecipeClass
import com.example.example.Recipes
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.datastore.ChosenRecipesDS
import com.lakhai.petprojectapplicationlakhai.data.recipes.RecipesGetter
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.RandomRecipeListener
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.toast.ToastForRandomRecipe
import com.lakhai.petprojectapplicationlakhai.data.webview.WebViewSettings
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityRandomrecipeBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class RandomrecipeActivity : AppCompatActivity() {
    var backPress=true
    var listOfRecipes: ArrayList<Recipes>? = null
    private var _binding: ActivityRandomrecipeBinding? = null
    val binding get() = _binding!!
    val managerForRandomRequest: RecipesGetter by inject()
    val toastImpl: ToastForRandomRecipe by inject { parametersOf(this@RandomrecipeActivity) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityRandomrecipeBinding.inflate(layoutInflater)
        val getIds =
            ChosenRecipesDS().getSharedPreferenceStringList(applicationContext, "key")
        for (i in getIds?.indices!!) {
            Log.d("teagsad", "${getIds[i].toString()},")
        }
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
                val id = responce.recipes[0].id
                val listRecipe = ChosenRecipesDS().getSharedPreferenceStringList(activity!!, "key")
                val listToGo = listRecipe!!.toMutableList()

                for (i in listToGo.indices) {
                    if (listToGo[i].toString().toInt() == id) {

                        activity?.binding?.favoriteButton?.setImageResource(R.drawable.baseline_favorite_24_active)
                        break
                    }
                }
                activity?.binding?.favoriteButton?.setOnClickListener {
                    activity?.binding?.backButton?.isClickable =false
                    activity?.backPress=false
                    Handler().postDelayed({
                        activity?.binding?.backButton?.isClickable =true
                        activity?.backPress=true
                    }, 1000)
                    val listRecipes =
                        ChosenRecipesDS().getSharedPreferenceStringList(activity!!, "key")
                    val listToGog = listRecipes!!.toMutableList()
                    if(listToGog.size==0){
                        addRecipe(id, listToGog)
                    }else{
                        for (i in listToGog.indices) {
                            if (listToGog[i].toString().toInt() == id) {
                                listToGog.removeAt(i)
                                val newList = listToGog.map { it?.toInt() }.toList()
                                ChosenRecipesDS().setSharedPreferenceStringList(
                                    activity!!, "key",
                                    newList.toList() as List<Int>
                                )
                                activity?.binding?.favoriteButton?.setImageResource(R.drawable.baseline_favorite_24)
                                activity?.toastImpl?.toastSetter("You delete this item \n from favorites")
                                break
                            } else if (listToGog.size - 1 == i) {
                                addRecipe(id, listToGog)
                            }
                        }
                    }

                }
                val ingredients = responce.recipes[0].extendedIngredients
                var recipeIngredients = "Ingredients:\n"
                for (index in ingredients.indices) {
                    recipeIngredients += "- ${ingredients[index].original}\n"
                }
                val title = responce.recipes[0].title?.replace("\\<[^>]*>", "")

                val instructions = responce.recipes[0].instructions?.replace("\\<[^>]*>", "")
                var ingredientsAndInstructions = recipeIngredients + "\n" + "\n" + instructions
                ingredientsAndInstructions= ingredientsAndInstructions.replace("<ol><li>","")
                activity?.binding?.deviliousRecipeTv?.text = title
                activity?.binding?.recipeTv?.text = ingredientsAndInstructions
                WebViewSettings().setWebView(activity!!, title.toString())


            }
        }

         fun addRecipe(id: Int?, listToGog: MutableList<String?>) {
            Log.d("ofgfgfdf", "ok,id id $id")
            listToGog.add(id.toString())
            val intList: MutableList<Int> =
                listToGog.map { it!!.toInt() }.toMutableList()
            ChosenRecipesDS().setSharedPreferenceStringList(
                activity!!,
                "key",
                intList
            )
            activity?.binding?.favoriteButton?.setImageResource(R.drawable.baseline_favorite_24_active)
            activity?.toastImpl?.toastSetter("You added this item \n to favorites")
        }

        override fun didError(message: String) {
            Log.d("ErrorsOfRandom", message)
            activity?.toastImpl?.toastSetter(message)
        }

    }

    override fun onBackPressed() {
        if(backPress){
            val activityEndIntent = Intent(this@RandomrecipeActivity, MenuActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }

    }
}