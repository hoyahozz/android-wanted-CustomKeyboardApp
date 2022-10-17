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
    activity: Activity,
    attrs: AttributeSet? = null,
    defStyleAttr: Int=0) : GridLayout(activity , attrs , defStyleAttr ) , View.OnLongClickListener  {

    private val binding = KeyboardShortcutKeysBinding.inflate(LayoutInflater.from(context), this, true)
    lateinit var popupWindow : PopupWindow


    val shortcutKeyXY =IntArray(2)
    val btnArr = arrayOf(
        binding.btn1,
        binding.btn2,
        binding.btn3,
        binding.btn4,
        binding.btn5,
        binding.btn6,
        binding.btn7,
        binding.btn8,
        binding.btn9,
        binding.btn10
    )
    val btnXY = arrayOf(
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
        IntArray(2),
    )


    fun getKey(et : EditText, tv : TextView ){
        shortkey = tv
        editText = et

        lateinit var shortcutKeyXY : IntArray
        var shortcutKeyWidth : Int = shortkey.width
        var shortcutKeyHight  : Int = shortkey.height

    }
    companion object{
        var isLongClick = false
        lateinit var shortkey : TextView
        lateinit var editText : EditText



    }
    init {

        Log.i("ttt","ggggggggggggggggg")

        shortkey.getLocationOnScreen(shortcutKeyXY)

        inflate(context , R.layout.keyboard_shortcut_keys , this)
        var text = "단"
        binding.btn1.text = text
        val btnWidth = arrayOf(10)
        val btn1Hight = arrayOf(10)
        popupWindow = PopupWindow(binding.root)

        for ( i in 0..9 ){
            btnWidth[i] = btnArr[i].width
            btn1Hight[i] = btnArr[i].height
            btnArr[i].getLocationOnScreen(btnXY[i])
        }



        shortkey.setOnClickListener {

            editText = activity.findViewById<EditText>(R.id.et_input)
            val shortcutKey = activity.findViewById<TextView>(R.id.action_key_short)

            isLongClick = false
            editText.text.append(shortcutKey.text)
        }
        shortkey.setOnLongClickListener(this)



    }

    fun showPopup(){

        popupWindow.apply {
            if (isShowing){
                update(50,0,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT)
            } else {
                width = ViewGroup.LayoutParams.WRAP_CONTENT
                height = ViewGroup.LayoutParams.WRAP_CONTENT
                isOutsideTouchable = true
                showAtLocation(shortkey,Gravity.CENTER,200,-100)
            }
        }

    }

    override fun onLongClick(v: View?): Boolean {
        isLongClick = true
        showPopup()
        Log.i("tt",";lllllllllllllllllllllllllllllllllllllllllllllllllllllllll")
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {




        return super.dispatchTouchEvent(ev)
    }
//
//    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
//
//        if (isLongClick) {
//            for (i in 0..9) {
//                btnArr[i].getLocationOnScreen(btnXY[i])
//            }
//
//            when ( ev!!.action ){
//
//                MotionEvent.ACTION_MOVE, MotionEvent.ACTION_DOWN -> {
//
//                if ((shortcutKeyXY[0] <= ev.x) && (ev.x <= shortcutKeyXY[0] + shortcutKeyWidth) && (shortcutKeyXY[1] <= ev.y) && (ev.y <= shortcutKeyXY[1] +shortcutKeyHight)) {
//                    // 버튼 클릭시.
//
//                }
//
//                if ((pop1XY[0] <= ev.x) && (ev.x <= pop1XY[0] + pop1Hight) && (pop1XY[1] <= ev.y) && (ev.y <= pop1XY[1] + pop1Hight)) {
//
//                    G.fixText = binding.popup.btn1.text.toString()
//                    TOUCH_UP_STATE = MotionEventUpState.BUTTON_1
//                    Log.i("BYN", "POP 1 View")
//
//
//                } else if ((pop2XY[0] <= ev.x) && (ev.x <= pop2XY[0] + pop2Hight) && (pop2XY[1] <= ev.y) && (ev.y <= pop2XY[1] + pop2Hight)) {
//
//                    G.fixText = binding.popup.btn2.text.toString()
//                    TOUCH_UP_STATE = MotionEventUpState.BUTTON_2
//                    Log.i("BYN", "POP2 View")
//                } else if ((pop3XY[0] <= ev.x) && (ev.x <= pop3XY[0] + pop3Hight) && (pop3XY[1] <= ev.y) && (ev.y <= pop3XY[1] + pop3Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn3.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_3
//            } else if ((pop4XY[0] <= ev.x) && (ev.x <= pop4XY[0] + pop4Hight) && (pop4XY[1] <= ev.y) && (ev.y <= pop4XY[1] + pop4Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn4.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_4
//            } else if ((pop5XY[0] <= ev.x) && (ev.x <= pop5XY[0] + pop5Hight) && (pop5XY[1] <= ev.y) && (ev.y <= pop5XY[1] + pop5Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn5.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_5
//            } else if ((pop6XY[0] <= ev.x) && (ev.x <= pop6XY[0] + pop6Hight) && (pop6XY[1] <= ev.y) && (ev.y <= pop6XY[1] + pop6Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn6.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_6
//            } else if ((pop7XY[0] <= ev.x) && (ev.x <= pop7XY[0] + pop7Hight) && (pop7XY[1] <= ev.y) && (ev.y <= pop7XY[1] + pop7Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn7.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_7
//            } else if ((pop8XY[0] <= ev.x) && (ev.x <= pop8XY[0] + pop1Hight) && (pop8XY[1] <= ev.y) && (ev.y <= pop8XY[1] + pop8Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn8.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_8
//            } else if ((pop9XY[0] <= ev.x) && (ev.x <= pop9XY[0] + pop9Hight) && (pop9XY[1] <= ev.y) && (ev.y <= pop9XY[1] + pop9Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn9.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_9
//            } else if ((pop10XY[0] <= ev.x) && (ev.x <= pop10XY[0] + pop10Hight) && (pop10XY[1] <= ev.y) && (ev.y <= pop10XY[1] + pop10Hight)) {
//                // 첫 번째 팝업 클릭
//                G.fixText = binding.popup.btn10.text.toString()
//                TOUCH_UP_STATE = MotionEventUpState.BUTTON_10
//            }
//
//            }
//
//            MotionEvent.ACTION_UP -> {
//                isLong = 0
//                when (TOUCH_UP_STATE) {
//                    MotionEventUpState.BUTTON_1 -> {
//                        binding.et.text.append(binding.popup.btn1.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//                    }
//                    MotionEventUpState.BUTTON_2 -> {
//                        binding.et.text.append(binding.popup.btn2.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_3 -> {
//                        binding.et.text.append(binding.popup.btn3.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_4 -> {
//                        binding.et.text.append(binding.popup.btn4.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_5 -> {
//                        binding.et.text.append(binding.popup.btn5.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_6 -> {
//                        binding.et.text.append(binding.popup.btn6.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_7 -> {
//                        binding.et.text.append(binding.popup.btn7.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_8 -> {
//                        binding.et.text.append(binding.popup.btn8.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_9 -> {
//                        binding.et.text.append(binding.popup.btn9.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    MotionEventUpState.BUTTON_10 -> {
//                        binding.et.text.append(binding.popup.btn10.text.toString())
//                        binding.btn.text = G.fixText
//                        binding.popup.root.visibility = View.INVISIBLE
//
//                    }
//                    else -> {
//
//                    }
//                }
//
//            }
//
//
//            }
//
//
//        }
//    }





}