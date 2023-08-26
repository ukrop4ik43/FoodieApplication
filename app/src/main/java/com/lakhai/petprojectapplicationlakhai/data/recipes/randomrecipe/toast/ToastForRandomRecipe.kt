package com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.toast

import android.content.Context
import android.widget.Toast

class ToastForRandomRecipe(val context: Context) {
    private var toastBuffer: Toast? = null
     fun toastSetter(string: String) {
        toastBuffer?.cancel()
        toastBuffer =
            Toast.makeText(context, string, Toast.LENGTH_SHORT)
        toastBuffer?.show()
    }
}