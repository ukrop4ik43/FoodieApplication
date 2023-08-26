package com.lakhai.petprojectapplicationlakhai.data.datastore

import android.content.Context
import android.media.MediaPlayer
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.appcompat.app.AppCompatActivity
import com.lakhai.petprojectapplicationlakhai.R

class SettingsDs  (val context: Context) {
    var mMediaPlayer: MediaPlayer? = null
    private val preferences = context.getSharedPreferences("xczxxzzxcv", Context.MODE_PRIVATE)
    private val editor = preferences.edit()

    fun setDataVibration(data: Boolean) {
        editor.putBoolean("content", data)
        editor.apply()
    }

    fun getDataVibration(): Boolean {
        return preferences.getBoolean("content", false)
    }

    fun setDataSound(data: Boolean) {
        editor.putBoolean("sound", data)
        editor.apply()
    }

    fun getDataSound(): Boolean {
        return preferences.getBoolean("sound", false)
    }
    fun playSound() {
        if(getDataSound()){
            mMediaPlayer = MediaPlayer.create(context, R.raw.click)
            mMediaPlayer!!.start()
        }
    }
}

fun AppCompatActivity.vibratePhone() {
    val vibrator = this.applicationContext?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    if (Build.VERSION.SDK_INT >= 26) {
        vibrator.vibrate(VibrationEffect.createOneShot(230, VibrationEffect.DEFAULT_AMPLITUDE))
    } else {
        vibrator.vibrate(230)
    }
}


fun AppCompatActivity.vibrateUserPhone() {

    if(SettingsDs(this@vibrateUserPhone.applicationContext).getDataVibration()){
        this.vibratePhone()
    }
}


