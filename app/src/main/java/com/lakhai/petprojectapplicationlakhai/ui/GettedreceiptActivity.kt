package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.datastore.ChosenRecipesDS
import com.lakhai.petprojectapplicationlakhai.data.recipes.RecipesGetter
import com.lakhai.petprojectapplicationlakhai.data.recipes.model.ingredientsrecipe.GettedIngredientReceipt
import com.lakhai.petprojectapplicationlakhai.data.recipes.model.instructions.RootInstructions
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.IngredientInstructionsListener
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.IngredientRecipeListener
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.toast.ToastForRandomRecipe
import com.lakhai.petprojectapplicationlakhai.data.webview.WebViewSettings
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityGettedreceiptBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.lang.Exception

class GettedreceiptActivity : AppCompatActivity() {
    private var _binding: ActivityGettedreceiptBinding? = null
    var backPress = true
    val binding get() = _binding!!
    val managerForRandomRequest: RecipesGetter by inject()
    val toastImpl: ToastForRandomRecipe by inject { parametersOf(this@GettedreceiptActivity) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGettedreceiptBinding.inflate(layoutInflater)

        binding.backButton.setOnClickListener {
            val activityEndIntent = Intent(this@GettedreceiptActivity, MenuActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }
        Log.d("Gerrted", intent.getStringExtra("Ingredients").toString())
        RecipeResponceListener.activity = this@GettedreceiptActivity
        RecipeInstructionListener.activity = this@GettedreceiptActivity
        managerForRandomRequest.getIngredientRecipes(
            RecipeResponceListener,
            intent.getStringExtra("Ingredients").toString()
        )
        setContentView(R.layout.load_receipt)
    }

    internal object RecipeResponceListener : IngredientRecipeListener {
        var activity: GettedreceiptActivity? = null
        override fun didFetch(responce: ArrayList<GettedIngredientReceipt>, message: String) {
            Log.d("ErrorsOfRandom", "good")
            try {
                activity?.let {
                    activity?.binding?.ivRecipe?.let { it1 ->
                        Glide.with(it)
                            .load(responce[0].image)
                            .fitCenter()
                            .circleCrop()
                            .into(it1)

                    }
                    val ingredients = responce[0].usedIngredients
                    val ingredientsNotUsed = responce[0].missedIngredients
                    var recipeIngredients = "Ingredients:\n"
                    if (ingredients != null) {
                        for (index in ingredients.indices) {
                            recipeIngredients += "- ${ingredients[index].original}\n"
                        }
                    }
                    if (ingredientsNotUsed != null) {
                        for (index in ingredientsNotUsed.indices) {
                            recipeIngredients += "- ${ingredientsNotUsed[index].original}\n"
                        }
                    }
                    val id = responce[0].id
                    val listRecipe = ChosenRecipesDS().getSharedPreferenceStringList(
                        activity!!, "key"
                    )
                    val listToGo = listRecipe!!.toMutableList()

                    for (i in listToGo.indices) {
                        if (listToGo[i].toString().toInt() == id) {

                            activity?.binding?.favoriteButton?.setImageResource(R.drawable.baseline_favorite_24_active)
                            break
                        }
                    }
                    activity?.binding?.favoriteButton?.setOnClickListener {
                        activity?.binding?.backButton?.isClickable = false
                        activity?.backPress = false
                        Handler().postDelayed({
                            activity?.binding?.backButton?.isClickable = true
                            activity?.backPress = true
                        }, 1000)
                        val listRecipes =
                            ChosenRecipesDS().getSharedPreferenceStringList(activity!!, "key")
                        val listToGog = listRecipes!!.toMutableList()
                        if (listToGog.size == 0) {
                            addRecipe(
                                id,
                                listToGog, activity!!
                            )
                        } else {
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
                                    addRecipe(
                                        id,
                                        listToGog, activity!!
                                    )
                                }
                            }
                        }
                    }
                    Log.d("Gerrted", responce[0].title?.replace("\\<[^>]*>", "").toString())
                    var title = responce[0].title?.replace("\\<[^>]*>", "")
                    title= title?.replace("<ol><li>","")
                    val instructionId = responce[0].id.toString()
                    Log.d("gettedId", instructionId)
                    var ingredientsAndInstructions = recipeIngredients + "\n" + "\n"
                    ingredientsAndInstructions= ingredientsAndInstructions.replace("<ol><li>","")
                    activity?.binding?.ingredientTvTitle?.text = title.toString()
                    activity?.binding?.recipeTv?.text =
                        ingredientsAndInstructions
                    activity!!.managerForRandomRequest.getIngredientInstructions(
                        RecipeInstructionListener,
                        instructionId, title.toString()
                    )
                    WebViewSettings().setWebViews(activity!!, title.toString())


                }
            } catch (e: Exception) {
                val activityEndIntent =
                    Intent(this@RecipeResponceListener.activity, RandomrecipeActivity::class.java)
                this@RecipeResponceListener.activity?.startActivity(activityEndIntent)
                this@RecipeResponceListener.activity?.finish()
            }

        }

        fun addRecipe(id: Int?, listToGog: MutableList<String?>, activity: GettedreceiptActivity) {
            Log.d("ofgfgfdf", "ok,id id $id")
            listToGog.add(id.toString())
            val intList: MutableList<Int> =
                listToGog.map { it!!.toInt() }.toMutableList()
            ChosenRecipesDS().setSharedPreferenceStringList(
                activity,
                "key",
                intList
            )
            activity.binding.favoriteButton.setImageResource(R.drawable.baseline_favorite_24_active)
            activity.toastImpl.toastSetter("You added this item \n to favorites")
        }

        override fun didError(message: String) {
            Log.d("ErrorsOfRandom", message)
            activity?.toastImpl?.toastSetter(message)
        }
    }


    internal object RecipeInstructionListener : IngredientInstructionsListener {
        var activity: GettedreceiptActivity? = null
        override fun didFetch(responce: RootInstructions, message: String, title: String) {
            Log.d("ErrorsOfRandom", "good")

            activity?.let {
                activity?.binding?.ivRecipe?.let { it1 ->
                    Glide.with(it)
                        .load(responce.image)
                        .fitCenter()
                        .circleCrop()
                        .into(it1)

                }
                val summary = responce.instructions?.replace("\\<[^>]*>", "").toString()
                var recipeIngredients = activity!!.binding.recipeTv.text.toString()
                recipeIngredients += summary
                Log.d("Gerrted", responce.title?.replace("\\<[^>]*>", "").toString())
                activity?.binding?.recipeTv?.text =
                    recipeIngredients
                WebViewSettings().setWebViews(RecipeResponceListener.activity!!, title)
            }
        }

        override fun didError(message: String) {
            Log.d("ErrorsOfRandom", message)
            activity?.toastImpl?.toastSetter(message)
        }

    }

    override fun onBackPressed() {
        if (backPress) {
            val activityEndIntent = Intent(this@GettedreceiptActivity, MenuActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }

    }
}