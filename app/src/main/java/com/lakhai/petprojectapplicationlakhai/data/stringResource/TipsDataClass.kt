package com.lakhai.petprojectapplicationlakhai.data.stringResource

data class TipsDataClass(
    val stringArray: Array<String> = arrayOf("Think Like a Factory Line,\n and Work Clean",
    "Freeze Liquids in\n Useable Portions! ",
    "Keep Your Knives Sharp!",
    "Use a Scale for Baking!")
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as TipsDataClass

        if (!stringArray.contentEquals(other.stringArray)) return false

        return true
    }

    override fun hashCode(): Int {
        return stringArray.contentHashCode()
    }
}
