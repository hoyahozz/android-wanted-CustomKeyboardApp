package com.preonboarding.customkeyboard.presentation.keyboard.shortcutkeys

import android.app.Activity
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.*
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.databinding.KeyboardShortcutKeysBinding
import java.util.*


class ShortcutPopup  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int=0) : GridLayout(context , attrs , defStyleAttr ) {

    lateinit var longClickListener: View.OnLongClickListener
    val binding = KeyboardShortcutKeysBinding.inflate(LayoutInflater.from(context), this, true)
    lateinit var popupWindow: PopupWindow
    lateinit var shortkey: View
    var e: MotionEvent? = null

        companion object {

        var isLongClick = false

        fun setEv(ev: MotionEvent?) {

        }
















        }


//
//        shortkey.getLocationOnScreen(shortcutKeyXY)
//        var text = "단"
//        binding.btn1.text = text
//        val btnWidth = arrayOf(10)
//        val btn1Hight = arrayOf(10)
//        popupWindow = PopupWindow(binding.root)
//
//        for ( i in 0..9 ){
//            btnWidth[i] = btnArr[i].width
//            btn1Hight[i] = btnArr[i].height
//            btnArr[i].getLocationOnScreen(btnXY[i])
//        }
    }
//
//    fun showPopup(){
//
//        popupWindow.apply {
//            if (isShowing){
//                update(50,0,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
//            } else {
//                width = ViewGroup.LayoutParams.WRAP_CONTENT
//                height = ViewGroup.LayoutParams.WRAP_CONTENT
//                isOutsideTouchable = true
//                showAtLocation(shortkey,Gravity.CENTER,200,-100)
//            }
//        }
//
//    }
//
//}