package com.preonboarding.customkeyboard.presentation.keyboard

import android.view.inputmethod.InputConnection

class KoreaLanguageMaker {

    private lateinit var inputConnection: InputConnection

    constructor(inputConnection: InputConnection) {
        this.inputConnection = inputConnection
    }

    open fun commit(c: Char){
        directlyCommit()
    }
    open fun directlyCommit(){

        inputConnection.commitText(toString(), 1)//입력부분

    }
}