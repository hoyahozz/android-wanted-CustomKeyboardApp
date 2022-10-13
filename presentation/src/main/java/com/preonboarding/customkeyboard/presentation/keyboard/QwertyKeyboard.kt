package com.preonboarding.customkeyboard.presentation.keyboard

import android.content.Context
import android.os.Build
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputConnection
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import com.preonboarding.customkeyboard.presentation.R
import dagger.hilt.android.AndroidEntryPoint


class QwertyKeyboard(
    private var context: Context,
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
    private val layoutLines = ArrayList<LinearLayout>()


    fun init() {
        qwertyKeyboardLayout =
            layoutInflater.inflate(R.layout.qwerty_keyboard, null) as LinearLayout

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
    }

    private fun qwertyKeyboardFindViewById(id: Int) =
        qwertyKeyboardLayout.findViewById<LinearLayout>(id)


    fun getLayout(): LinearLayout {
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

                with(context){
                    mainKeyText.let {
                        when (qwertyMainKeyboardText[keyLine][keyItem]) {
                            getString(R.string.key_space) -> setSpecialKey(it, R.string.key_space)
                            getString(R.string.key_back) -> setSpecialKey(it, R.string.key_back)
                            getString(R.string.key_shift) -> setSpecialKey(it, R.string.key_shift)
                            getString(R.string.key_enter) -> setSpecialKey(it, R.string.key_enter)
                            getString(R.string.key_short) -> setSpecialKey(it, R.string.key_short)
                            getString(R.string.key_special) -> setSpecialKey(it, R.string.key_special)
                            else -> {
                                mainKeyText.text = qwertyMainKeyboardText[keyLine][keyItem]
                                if (keyLine in 1..3) {
                                    subKeyText.apply {
                                        text = qwertySubKeyboardText[keyLine - 1][keyItem]
                                        visibility = View.VISIBLE
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun setSpecialKey(txt : TextView, string : Int) {
        txt.text = context.getString(string)
        txt.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12F)
    }
}