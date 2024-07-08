package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.datastore.SettingsDs
import com.lakhai.petprojectapplicationlakhai.data.datastore.vibrateUserPhone
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityMenuBinding
import org.koin.android.ext.android.inject

class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    lateinit var toggle: ActionBarDrawerToggle
    var interAd: InterstitialAd? = null

    var randomFlag = false
    val settin gsDs: SettingsDs by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdMob()
        binding.apply {
            toggle = ActionBarDrawerToggle(
                this@MenuActivity,
                drawerLayout,
                R.string.open,
                R.string.close
            )
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.second -> {
                        val activityEndIntent =
                            Intent(this@MenuActivity, SettingsActivity::class.java)
                        startActivity(activityEndIntent)
                        finish()
                    }

                    R.id.third -> {
                        val myIntent = Intent(this@MenuActivity, InfoActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }

                    R.id.fourth -> {
                        val myIntent = Intent(this@MenuActivity, ChosenActivity::class.java)
                        startActivity(myIntent)
                        finish()
                    }
                }
                true
            }
            buttonForMyImageMenu.setOnClickListener {
                settingsDs.playSound()
                this@MenuActivity.vibrateUserPhone()
                drawerLayout.openDrawer(GravityCompat.START);
            }
            randomButton.setOnClickListener {
                settingsDs.playSound()
                this@MenuActivity.vibrateUserPhone()
                randomFlag = true
                showInterAd()


            }
            ivRecipe.setOnClickListener {
                settingsDs.playSound()
                this@MenuActivity.vibrateUserPhone()
                randomFlag = false
                showInterAd()

            }
        }


    }

    override fun onResume() {
        super.onResume()
        binding.adViewq.resume()
        loadInterAd()
    }

    override fun onPause() {
        super.onPause()
        binding.adViewq.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.adViewq.destroy()
    }

    private fun initAdMob() {
        MobileAds.initialize(this@MenuActivity)
        val adRequest = AdRequest.Builder().build()
        binding.adViewq.loadAd(adRequest)

    }

    private fun showInterAd() {
        if (interAd != null) {
            interAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdDismissedFullScreenContent() {
                    showContent()
                    interAd = null
                    loadInterAd()
                }

                override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                    showContent()
                    interAd = null
                    loadInterAd()
                }

                override fun onAdShowedFullScreenContent() {

                    interAd = null
                    loadInterAd()
                }

            }
            interAd?.show(this@MenuActivity)
        } else {
            showContent()
        }
    }

    private fun showContent() {
        if (randomFlag) {
            val activityEndIntent = Intent(this@MenuActivity, RandomrecipeActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        } else {
            val activityEndIntent = Intent(this@MenuActivity, GettedreceiptActivity::class.java)
            startActivity(activityEndIntent)
            finish()
        }
    }

    fun loadInterAd() {
        val adRequest = AdRequest.Builder().build()
        InterstitialAd.load(this@MenuActivity,
            "ca-app-pub-1010843762178620/8391109494",
            adRequest, object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(p0: LoadAdError) {
                    interAd = null

                }

                override fun onAdLoaded(p0: InterstitialAd) {
                    interAd = p0
                }
            })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)) {
            true
        }
        return super.onOptionsItemSelected(item)
    }
}