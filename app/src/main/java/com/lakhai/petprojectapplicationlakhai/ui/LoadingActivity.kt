package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.lakhai.petprojectapplicationlakhai.data.splash.MenuLogic
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityLoadingBinding
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf


class LoadingActivity : AppCompatActivity() {
    private val menuSetter: MenuLogic by inject { parametersOf(this@LoadingActivity) }
    private var _binding: ActivityLoadingBinding? = null
    val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoadingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        menuSetter.setTextWithAnimation()
        Handler(Looper.getMainLooper()).postDelayed({
            val activityEndIntent = Intent(this@LoadingActivity, MenuActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }, 3000)
    }
}