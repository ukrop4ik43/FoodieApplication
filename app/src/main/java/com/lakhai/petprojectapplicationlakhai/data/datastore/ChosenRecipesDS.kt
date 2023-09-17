package com.lakhai.petprojectapplicationlakhai.data.datastore

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.provider.SyncStateContract.Constants
import com.lakhai.petprojectapplicationlakhai.data.constants.ConstantsObject.PREF_CHOOSEN


class ChosenRecipesDS {
    fun setSharedPreferenceStringList(pContext: Context, pKey: String, pData: List<Int>) {
        val editor: SharedPreferences.Editor =
            pContext.getSharedPreferences(PREF_CHOOSEN, Activity.MODE_PRIVATE).edit();
        editor.putInt(pKey + "size", pData.size);
        editor.apply();
        for (i in pData.indices) {
            val editor1: SharedPreferences.Editor =
                pContext.getSharedPreferences(PREF_CHOOSEN, Activity.MODE_PRIVATE).edit();
            editor1.putString(pKey + i, (pData[i].toString()));
            editor1.apply()
        }
    }

    fun getSharedPreferenceStringList(pContext: Context, pKey: String): List<String?>? {
        val size = pContext.getSharedPreferences(PREF_CHOOSEN, Activity.MODE_PRIVATE)
            .getInt(pKey + "size", 0)
        val list: MutableList<String?> = ArrayList()
        for (i in 0 until size) {
            list.add(
                pContext.getSharedPreferences(PREF_CHOOSEN, Activity.MODE_PRIVATE)
                    .getString(pKey + i, "")
            )
        }
        return list
    }

}