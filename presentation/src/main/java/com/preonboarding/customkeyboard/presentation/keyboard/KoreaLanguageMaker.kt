package com.preonboarding.customkeyboard.presentation.keyboard

import android.view.inputmethod.InputConnection
import javax.inject.Inject

class KoreaLanguageMaker @Inject constructor(
    private val inputConnection: InputConnection
){
    open fun commit(c: Char){
        directlyCommit()
    }
    open fun directlyCommit(){

        inputConnection.commitText(toString(), 1)//입력부분

    }
}