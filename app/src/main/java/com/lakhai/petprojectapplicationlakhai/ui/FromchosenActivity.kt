package com.lakhai.petprojectapplicationlakhai.ui


import com.bumptech.glide.Glide


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.activity.OnBackPressedCallback
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.chosen.ChosenModel
import com.lakhai.petprojectapplicationlakhai.data.chosen.fromchosen.RecipeFromGetter
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.FavoriteRecipe
import com.lakhai.petprojectapplicationlakhai.data.datastore.ChosenRecipesDS
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.FavoriteModelListener
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.toast.ToastForRandomRecipe
import com.lakhai.petprojectapplicationlakhai.data.webview.WebViewSettings
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityGettedreceiptBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import java.lang.Exception

class FromchosenActivity : AppCompatActivity() {
    val toastImpl: ToastForRandomRecipe by inject { parametersOf(this@FromchosenActivity) }
    private var _binding: ActivityGettedreceiptBinding? = null
    var backPress = true
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityGettedreceiptBinding.inflate(layoutInflater)
        onBack()
        binding.backButton.setOnClickListener {
            val activityEndIntent = Intent(this@FromchosenActivity, MenuActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }
        val idGetted=intent.getIntExtra ("Username",0)
        RecipeResponceListener.activity = this@FromchosenActivity
        val managerForFavoriteRequest= RecipeFromGetter(this@FromchosenActivity)
        managerForFavoriteRequest.getIngredientInstructions(
            RecipeResponceListener,
            idGetted.toString(), 1
        )
    }
    internal object RecipeResponceListener : FavoriteModelListener {
        var activity: FromchosenActivity? = null
        override fun didFetch(responce: FavoriteRecipe, message: String, i: Int) {
            Log.d("ErrorsOfRandom", "good")
            try {
                Log.d("Gerrted", responce.title?.replace("\\<[^>]*>", "").toString())
                var title = responce.title?.replace("\\<[^>]*>", "")
                Log.d("coming activity","yes")

                val instructionId = responce.id.toString()
                activity?.binding?.ingredientTvTitle?.text  =title
                Log.d("gettedId", instructionId)
                activity?.binding?.ivRecipe?.let { it1 ->
                    Glide.with(activity!!)
                        .load(responce.image)
                        .fitCenter()
                        .circleCrop()
                        .into(it1)
                }


                val listRecipe = ChosenRecipesDS().getSharedPreferenceStringList(
                    activity!!, "key")
                val listToGo = listRecipe!!.toMutableList()

                for (i in listToGo.indices) {
                    if (listToGo[i].toString().toInt() == instructionId.toInt()) {

                        activity?.binding?.favoriteButton?.setImageResource(
                            R.drawable.baseline_favorite_24_active)
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
                        RandomrecipeActivity.randomRecipeResponceListener.addRecipe(instructionId.toInt(), listToGog)
                    }else{
                        for (i in listToGog.indices) {
                            if (listToGog[i].toString().toInt() == instructionId.toInt()) {
                                listToGog.removeAt(i)
                                val newList = listToGog.map { it?.toInt() }.toList()
                                ChosenRecipesDS().setSharedPreferenceStringList(
                                    activity!!, "key",
                                    newList.toList() as List<Int>
                                )
                                activity?.binding?.favoriteButton?.setImageResource(
                                    R.drawable.baseline_favorite_24)
                                activity?.toastImpl?.toastSetter("You delete this item \n from favorites")
                                break
                            } else if (listToGog.size - 1 == i) {
                                RandomrecipeActivity.randomRecipeResponceListener.addRecipe(
                                    instructionId.toInt(),
                                    listToGog
                                )
                            }
                        }
                    }

                }
                val ingredients = responce.extendedIngredients
                var recipeIngredients = "Ingredients:\n"
                for (index in ingredients.indices) {
                    recipeIngredients += "- ${ingredients[index].original}\n"
                }

                val instructions = responce.instructions?.replace("\\<[^>]*>", "")
                var ingredientsAndInstructions = recipeIngredients + "\n" + "\n" + instructions
                ingredientsAndInstructions= ingredientsAndInstructions.replace("<ol><li>","")
                activity?.binding?.recipeTv?.text = ingredientsAndInstructions
                WebViewSettings().setWebViewsS(activity!!, title.toString())
                activity?.setContentView(activity!!.binding.root)

            } catch (e: Exception) {
                Log.d("someError", "error")
            }

        }

        override fun didError(message: String) {
            Log.d("ErrorsOfRandom", message)
        }
    }
    private fun onBack() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val myIntent = Intent(this@FromchosenActivity, MenuActivity::class.java)
                startActivity(myIntent)
                finish()
            }
        })
    }
}