package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.MobileAds
import com.lakhai.petprojectapplicationlakhai.data.datastore.SettingsDs
import com.lakhai.petprojectapplicationlakhai.data.datastore.vibrateUserPhone
import com.lakhai.petprojectapplicationlakhai.data.recipes.RecipesGetter
import com.lakhai.petprojectapplicationlakhai.databinding.ActivitySettingsBinding
import org.koin.android.ext.android.inject

class SettingsActivity : AppCompatActivity() {
    val settingsDs: SettingsDs by inject()
    private var _binding: ActivitySettingsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdMob()

        binding.vibrationSwitch.isChecked = settingsDs.getDataVibration()==true
        binding.soundSwitch.isChecked = settingsDs.getDataSound()==true
        binding.vibrationSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                this@SettingsActivity.vibrateUserPhone()
                settingsDs.setDataVibration(true)
            } else {
                settingsDs.setDataVibration(false)
            }
        }
        binding.soundSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                settingsDs.playSound()
                settingsDs.setDataSound(true)
            } else {
                settingsDs.setDataSound(false)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        binding.adViews.resume()
    }

    override fun onPause() {
        super.onPause()
        binding.adViews.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.adViews.destroy()
    }
    private fun initAdMob(){
        MobileAds.initialize(this@SettingsActivity)
        val adRequest = AdRequest.Builder().build()
        binding.adViews.loadAd(adRequest)

    }

    override fun onBackPressed() {
        val activityEndIntent = Intent(this@SettingsActivity, MenuActivity::class.java)
        startActivity(activityEndIntent)
        finish()
    }
}