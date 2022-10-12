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
        arrayOf("Shift", "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", "DEL"),
        arrayOf("!@#", ",", "SPACE", ".", "Enter")
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

        val numberLine = qwertyKeyboardLayout.findViewById<LinearLayout>(
            R.id.numpad_line
        )
        val firstLine = qwertyKeyboardLayout.findViewById<LinearLayout>(
            R.id.first_line
        )
        val secondLine = qwertyKeyboardLayout.findViewById<LinearLayout>(
            R.id.second_line
        )
        val thirdLine = qwertyKeyboardLayout.findViewById<LinearLayout>(
            R.id.third_line
        )
        val fourthLine = qwertyKeyboardLayout.findViewById<LinearLayout>(
            R.id.fourth_line
        )

        layoutLines.clear()
        layoutLines.add(numberLine)
        layoutLines.add(firstLine)
        layoutLines.add(secondLine)
        layoutLines.add(thirdLine)
        layoutLines.add(fourthLine)

        setLayoutComponents()
    }

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

                when (qwertyMainKeyboardText[keyLine][keyItem]) {
                    "SPACE" -> {
                        mainKeyText.text = "SPACE"
                    }
                    "DEL" -> {
                        mainKeyText.text = "Back"
                    }
                    "Shift" -> {
                        mainKeyText.text = "Shift"
                    }
                    "Enter" -> {
                        mainKeyText.text = "Enter"
                    }
                    "!@#" -> {
                        mainKeyText.text = "!@#"
                    }
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