package com.preonboarding.customkeyboard.presentation.model

data class ShortcutPopupCoordinate (
    val XY : IntArray,
    val width : Float,
    val hight : Float,
    ) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ShortcutPopupCoordinate

        if (!XY.contentEquals(other.XY)) return false
        if (width != other.width) return false
        if (hight != other.hight) return false

        return true
    }

    override fun hashCode(): Int {
        var result = XY.contentHashCode()
        result = 31 * result + width.hashCode()
        result = 31 * result + hight.hashCode()
        return result
    }
}
