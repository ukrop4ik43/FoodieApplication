package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.lakhai.petprojectapplicationlakhai.data.chosen.model.FavoriteRecipe
import com.lakhai.petprojectapplicationlakhai.data.chosen.ChosenAdapter
import com.lakhai.petprojectapplicationlakhai.data.chosen.ChosenModel
import com.lakhai.petprojectapplicationlakhai.data.chosen.FavoriteRecipeGetter
import com.lakhai.petprojectapplicationlakhai.data.datastore.ChosenRecipesDS
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.FavoriteModelListener
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityChosenBinding
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject
import java.lang.Exception

class ChosenActivity : AppCompatActivity() {
    var listToTransfer: List<String?>? = null
    private var _binding: ActivityChosenBinding? = null
    private val binding get() = _binding!!
    val managerForFavoriteRequest: FavoriteRecipeGetter by inject()
    var myModels: ArrayList<ChosenModel>? = arrayListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityChosenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdMob()
        onBack()
        binding.backButton.setOnClickListener {
            val myIntent = Intent(this@ChosenActivity, MenuActivity::class.java)
            startActivity(myIntent)
            finish()
        }
        binding.recyclerFavorite.layoutManager = LinearLayoutManager(this)
        runBlocking {
            listToTransfer =
                ChosenRecipesDS().getSharedPreferenceStringList(applicationContext, "key")
            RecipeResponceListener.activity = this@ChosenActivity
            for (i in listToTransfer?.indices!!) {
                Log.d("ofgfgfdf", "id is ${listToTransfer!![i]}")
                managerForFavoriteRequest.getIngredientInstructions(
                    RecipeResponceListener,
                    listToTransfer!![i].toString(), i
                )
            }

        }


    }

    internal object RecipeResponceListener : FavoriteModelListener {
        var activity: ChosenActivity? = null
        override fun didFetch(responce: FavoriteRecipe, message: String, i: Int) {
            Log.d("ErrorsOfRandom", "good")
            try {
                Log.d("Gerrted", responce.title?.replace("\\<[^>]*>", "").toString())
                var title = responce.title?.replace("\\<[^>]*>", "")
                title = title?.replace(" ", "\n")
                val instructionId = responce.id.toString()
                Log.d("gettedId", instructionId)
                val chosenModel =
                    responce.id?.let { ChosenModel(it, title.toString(), responce.image) }
                chosenModel?.let { activity?.myModels?.add(it) }
                val size = activity?.listToTransfer?.size?.minus(1)
                if (i == size) {
                    activity?.binding?.recyclerFavorite?.adapter = ChosenAdapter(
                        activity?.myModels!!,
                        activity!!
                    )
                }

            } catch (e: Exception) {
                Log.d("someError", "error")
            }

        }

        override fun didError(message: String) {
            Log.d("ErrorsOfRandom", message)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.adView.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.adView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.adView.destroy()
    }

    private fun initAdMob() {
        MobileAds.initialize(this@ChosenActivity)
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)

    }

    private fun onBack() {
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val myIntent = Intent(this@ChosenActivity, MenuActivity::class.java)
                startActivity(myIntent)
                finish()
            }
        })
    }
}