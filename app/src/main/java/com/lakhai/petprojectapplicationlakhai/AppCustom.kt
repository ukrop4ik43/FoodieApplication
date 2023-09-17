package com.lakhai.petprojectapplicationlakhai

import android.app.Application
import com.lakhai.petprojectapplicationlakhai.data.chosen.FavoriteRecipeGetter
import com.lakhai.petprojectapplicationlakhai.data.datastore.SettingsDs
import com.lakhai.petprojectapplicationlakhai.data.recipes.RecipesGetter
import com.lakhai.petprojectapplicationlakhai.data.recipes.randomrecipe.toast.ToastForRandomRecipe
import com.lakhai.petprojectapplicationlakhai.data.splash.MenuLogic
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext
import org.koin.dsl.module

class AppCustom:Application() {
    override fun onCreate() {
        super.onCreate()
        GlobalContext.startKoin {
            androidContext(this@AppCustom)
            modules(myModule)
        }
    }
    private val myModule = module {
        single { param -> MenuLogic(param.get()) }
        single { param -> ToastForRandomRecipe(param.get()) }
        single { RecipesGetter(get()) }
        single { SettingsDs(get()) }
        single { FavoriteRecipeGetter(get()) }
    }
}