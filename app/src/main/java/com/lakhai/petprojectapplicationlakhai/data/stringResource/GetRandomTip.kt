package com.lakhai.petprojectapplicationlakhai.data.stringResource

import java.util.*


fun getRandomTip(): String {
    return TipsDataClass().stringArray[(0 until TipsDataClass().stringArray.size).random()]
}