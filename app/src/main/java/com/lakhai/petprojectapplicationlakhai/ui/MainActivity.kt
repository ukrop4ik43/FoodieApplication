package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.datastore.ChosenRecipesDS
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private var mEnlargeAnimation: Animation? = null
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    var flagForClick = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivRecipe.setOnClickListener {
            if (!flagForClick) {
                flagForClick = true
                mEnlargeAnimation = AnimationUtils.loadAnimation(this, R.anim.shrink_anim);
                binding.chefIv.startAnimation(mEnlargeAnimation)
                binding.findTv.startAnimation(mEnlargeAnimation)
                Handler(Looper.getMainLooper()).postDelayed({
                    val activityEndIntent = Intent(this@MainActivity, LoadingActivity::class.java)
                    startActivity(activityEndIntent)
                    finish()
                }, 1300)
            }

        }
    }
}