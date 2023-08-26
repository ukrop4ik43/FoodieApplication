package com.lakhai.petprojectapplicationlakhai.data.splash

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import com.lakhai.petprojectapplicationlakhai.data.stringResource.getRandomTip
import com.lakhai.petprojectapplicationlakhai.ui.LoadingActivity

class MenuLogic( val activity:LoadingActivity) {
    @SuppressLint("SetTextI18n")
     fun setTextWithAnimation() {
        activity.binding.tvForTip.text = "Tip of the day:${getRandomTip()}"
        val rotation = ObjectAnimator.ofFloat(activity.binding.loadingIv, "rotation", 0f, 360f)
        rotation.duration = 3000
        rotation.repeatCount = 2
        rotation.start()
    }
}