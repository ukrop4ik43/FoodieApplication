package com.lakhai.petprojectapplicationlakhai.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import com.lakhai.petprojectapplicationlakhai.R
import com.lakhai.petprojectapplicationlakhai.data.datastore.SettingsDs
import com.lakhai.petprojectapplicationlakhai.data.datastore.vibrateUserPhone
import com.lakhai.petprojectapplicationlakhai.databinding.ActivityMenuBinding
import org.koin.android.ext.android.inject

class MenuActivity : AppCompatActivity() {
    lateinit var binding : ActivityMenuBinding
    lateinit var toggle: ActionBarDrawerToggle
    val settingsDs: SettingsDs by inject()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            toggle = ActionBarDrawerToggle(this@MenuActivity, drawerLayout, R.string.open, R.string.close)
            drawerLayout.addDrawerListener(toggle)
            toggle.syncState()

            supportActionBar?.setDisplayHomeAsUpEnabled(true)

            navView.setNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.second -> {
                        val activityEndIntent = Intent(this@MenuActivity, SettingsActivity::class.java)
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
                val activityEndIntent = Intent(this@MenuActivity, RandomrecipeActivity::class.java)
                startActivity(activityEndIntent)
                finish()
            }
            ivRecipe.setOnClickListener{
                settingsDs.playSound()
                this@MenuActivity.vibrateUserPhone()
                val activityEndIntent = Intent(this@MenuActivity, ReceiptsActivity::class.java)
                startActivity(activityEndIntent)
                finish()
            }
        }


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            true
        }
        return super.onOptionsItemSelected(item)
    }
}