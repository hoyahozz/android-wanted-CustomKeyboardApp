package com.preonboarding.customkeyboard.presentation.keyboard


import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.util.TypedValue
import android.view.*
import android.view.inputmethod.InputConnection
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.view.children
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.google.android.material.internal.ContextUtils.getActivity
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.keyboard.shortcutkeys.MotionEventUpState
import com.preonboarding.customkeyboard.presentation.keyboard.shortcutkeys.ShortcutPopup
import com.preonboarding.customkeyboard.presentation.ui.input.InputActivity
import com.preonboarding.customkeyboard.presentation.ui.main.MainActivity


class QwertyKeyboard (
    private var  context: Context,
    private var layoutInflater: LayoutInflater
) {
    private lateinit var qwertyKeyboardLayout: LinearLayout
    private val qwertyMainKeyboardText = arrayOf(
        arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"),
        arrayOf("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅛ", "ㅕ", "ㅑ", "ㅐ", "ㅔ"),
        arrayOf("ㅁ", "ㄴ", "ㅇ", "ㄹ", "ㅎ", "ㅗ", "ㅓ", "ㅏ", "ㅣ"),
        arrayOf(context.getString(R.string.key_shift), "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", context.getString(R.string.key_back)),
        arrayOf(
            context.getString(R.string.key_special),
            context.getString(R.string.key_short),
            ",",
            context.getString(R.string.key_space),
            ".",
            context.getString(R.string.key_enter)
        )
    )
    private val qwertySubKeyboardText = arrayOf(
        arrayOf("ㅃ", "ㅉ", "ㄸ", "ㄲ", "ㅆ", "/", "<", ">", "ㅒ", "ㅖ"),
        arrayOf("!", "@", "#", "%", "^", "&", "*", "(", ")"),
        arrayOf("", "-", "\'", "\"", ":", ";", ",", "?")
    )
    private val shortcutKeyText = listOf("", "ㅋㅋㅋㅋ", "ㅠㅠㅠㅠ", "안녕하세요", "감사합니다", "", "", " ?", "❤", "\uD83D\uDE04")
    private var shortcutText : String = ","
    private lateinit var shortcutKeyPopup: PopupWindow
    private val layoutLines = ArrayList<LinearLayout>()
    var inputConnection: InputConnection? = null
    lateinit var koreaLanguageMaker: KoreaLanguageMaker
    var isCaps: Boolean = false
    private val SHIFT_CHANGE_LINE = 1
    private var TOUCH_UP_STATE = MotionEventUpState.NONE
    val popup = PopupWindow(context)

    var shortKeyisLongClick = false
    var liveText: MutableLiveData<MotionEvent> = MutableLiveData()

    var shortcutKeyXY = IntArray(2)
    var shortcutWidth = 0
    var shortcutHight = 0

    var btn1XY = IntArray(2)
    var btn2XY = IntArray(2)
    var btn3XY = IntArray(2)
    var btn4XY = IntArray(2)
    var btn5XY = IntArray(2)
    var btn6XY = IntArray(2)
    var btn7XY = IntArray(2)
    var btn8XY = IntArray(2)
    var btn9XY = IntArray(2)
    var btn10XY = IntArray(2)

    var btnHightArr = IntArray(10)
    var btnWidthArr = IntArray(10)


    fun init() {
        qwertyKeyboardLayout =
            layoutInflater.inflate(R.layout.qwerty_keyboard, null) as LinearLayout

        koreaLanguageMaker = KoreaLanguageMaker(inputConnection!!)

        val numberLine = qwertyKeyboardFindViewById(R.id.numpad_line)
        val firstLine = qwertyKeyboardFindViewById(R.id.first_line)
        val secondLine = qwertyKeyboardFindViewById(R.id.second_line)
        val thirdLine = qwertyKeyboardFindViewById(R.id.third_line)
        val fourthLine = qwertyKeyboardFindViewById(R.id.fourth_line)


        layoutLines.apply {
            clear()
            add(numberLine)
            add(firstLine)
            add(secondLine)
            add(thirdLine)
            add(fourthLine)
        }

        setLayoutComponents()
        initShortcut()


    }

    private fun qwertyKeyboardFindViewById(id: Int) =
        qwertyKeyboardLayout.findViewById<LinearLayout>(id)



    fun getLayout(): LinearLayout {
        koreaLanguageMaker = KoreaLanguageMaker(inputConnection!!)
        setLayoutComponents()
        return qwertyKeyboardLayout
    }

    private fun setLayoutComponents() {
        for (keyLine in qwertyMainKeyboardText.indices) {
            for (keyItem in qwertyMainKeyboardText[keyLine].indices) {

                val layout = layoutLines[keyLine].children.toList()
                val keyButton = layout[keyItem].findViewById<ConstraintLayout>(R.id.key_button)
                val mainKeyText = layout[keyItem].findViewById<TextView>(R.id.main_key_text)
                val subKeyText = layout[keyItem].findViewById<TextView>(R.id.sub_key_text)


                var keyboardListener: View.OnClickListener? = null


                with(context){
                    mainKeyText.let {
                        when (qwertyMainKeyboardText[keyLine][keyItem]) {
                            getString(R.string.key_space) -> {
                                setSpecialKey(it, R.string.key_space)
                                keyboardListener = clickSpaceKeyListener()
                            }
                            getString(R.string.key_back) -> {
                                setSpecialKey(it, R.string.key_back)
                                keyboardListener = clickBackKeyListener()
                            }
                            getString(R.string.key_shift) -> {
                                setSpecialKey(it, R.string.key_shift)
                                keyboardListener = clickShiftKeyListener()
                            }
                            getString(R.string.key_enter) -> setSpecialKey(it, R.string.key_enter)
//                            getString(R.string.key_short) -> setSpecialKey(
//                                it,
//                                R.string.key_short
//                            )
                            getString(R.string.key_special) -> setSpecialKey(
                                it,
                                R.string.key_special
                            )
                            else -> {
                                mainKeyText.text = qwertyMainKeyboardText[keyLine][keyItem]
                                if (keyLine in 1..3) {
                                    subKeyText.apply {
                                        text = qwertySubKeyboardText[keyLine - 1][keyItem]
                                        visibility = View.VISIBLE
                                    }
                                }
                                keyboardListener = clickKeyboardListener(mainKeyText)
                                if (keyLine==4 && keyItem == 1){//단버튼 위치
                                    layout[keyItem].setOnLongClickListener {
                                        longClickShortcutKey()
                                        shortcutTextView.text = shortcutText
                                        true
                                    }
                                }
                            }
                        }
                        layout[keyItem].setOnClickListener(keyboardListener)
                    }
                }
            }
        }
    }

    private fun initShortcut(){

        val custom: View = LayoutInflater.from(context)
            .inflate(R.layout.keyboard_shortcut_keys, GridLayout(context))
        popup.contentView = custom

        val ShortcutViews = arrayOf(
            custom.findViewById<TextView>(R.id.btn1),
            custom.findViewById<TextView>(R.id.btn2),
            custom.findViewById<TextView>(R.id.btn3),
            custom.findViewById<TextView>(R.id.btn4),
            custom.findViewById<TextView>(R.id.btn5),
            custom.findViewById<TextView>(R.id.btn6),
            custom.findViewById<TextView>(R.id.btn7),
            custom.findViewById<TextView>(R.id.btn8),
            custom.findViewById<TextView>(R.id.btn9),
            custom.findViewById<TextView>(R.id.btn10)

        )

        for (i in 0..9){
            ShortcutViews[i].setOnClickListener {
                inputConnection?.commitText(shortcutKeyText[i], 1)
                shortcutText = ShortcutViews[i].text.toString()
                popup.dismiss()
            }
        }

    }

    private fun longClickShortcutKey(){

        showPopup()

    }



    fun showPopup(){
        if(popup.isShowing){
            popup.update(30, 0, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        } else {
            popup.width = ViewGroup.LayoutParams.WRAP_CONTENT
            popup.height = ViewGroup.LayoutParams.WRAP_CONTENT
            popup.isOutsideTouchable = true
            popup.showAtLocation(qwertyKeyboardLayout, Gravity.CENTER, 0, 0)
        }


    }


    private fun clickKeyboardListener(keyTxt: TextView): View.OnClickListener {
        val clickListener = (View.OnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                inputConnection?.requestCursorUpdates(InputConnection.CURSOR_UPDATE_IMMEDIATE)
            }

            try {
                //숫자가 아니면 오류가 발생함
                Integer.parseInt(keyTxt.text.toString())

                //숫자라면 기존 한국어 초기화
                koreaLanguageMaker.directCommitHangul()
                inputConnection?.commitText(keyTxt.text.toString(), 1)
            } catch (e: NumberFormatException) {
                koreaLanguageMaker.commitHangul(keyTxt.text.toString().toCharArray().get(0))
            }
            if (isCaps) changeCaps()

        })
        keyTxt.setOnClickListener(clickListener)

        return clickListener
    }
    private fun clickShiftKeyListener() = View.OnClickListener {
        changeCaps()
    }

    private fun changeCaps() {
        for (keyNum in qwertyMainKeyboardText[SHIFT_CHANGE_LINE].indices) {
            val layout = layoutLines[SHIFT_CHANGE_LINE].children.toList()
            val mainKeyText = layout[keyNum].findViewById<TextView>(R.id.main_key_text)

            if (isCaps) {
                when (mainKeyText.text.toString()) {
                    "ㅃ" -> {
                        mainKeyText.text = "ㅂ"
                    }
                    "ㅉ" -> {
                        mainKeyText.text = "ㅈ"
                    }
                    "ㄸ" -> {
                        mainKeyText.text = "ㄷ"
                    }
                    "ㄲ" -> {
                        mainKeyText.text = "ㄱ"
                    }
                    "ㅆ" -> {
                        mainKeyText.text = "ㅅ"
                    }
                    "ㅒ" -> {
                        mainKeyText.text = "ㅐ"
                    }
                    "ㅖ" -> {
                        mainKeyText.text = "ㅔ"
                    }
                }
            } else {
                when (mainKeyText.text.toString()) {
                    "ㅂ" -> {
                        mainKeyText.text = "ㅃ"
                    }
                    "ㅈ" -> {
                        mainKeyText.text = "ㅉ"
                    }
                    "ㄷ" -> {
                        mainKeyText.text = "ㄸ"
                    }
                    "ㄱ" -> {
                        mainKeyText.text = "ㄲ"
                    }
                    "ㅅ" -> {
                        mainKeyText.text = "ㅆ"
                    }
                    "ㅐ" -> {
                        mainKeyText.text = "ㅒ"
                    }
                    "ㅔ" -> {
                        mainKeyText.text = "ㅖ"
                    }
                }
            }

        }
        isCaps = !isCaps
    }
    private fun setSpecialKey(txt : TextView, string : Int) {
        txt.text = context.getString(string)
        txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
    }

    private fun clickSpaceKeyListener() = View.OnClickListener {
        koreaLanguageMaker.commitSpace()
    }

    private fun clickBackKeyListener() = View.OnClickListener {
        koreaLanguageMaker.deleteHangul()
    }
    companion object{
     var activity :Activity? = null
    }


}