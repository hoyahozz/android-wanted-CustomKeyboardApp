package com.preonboarding.customkeyboard.presentation.ui.input

import android.os.Bundle
import androidx.activity.viewModels
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.databinding.ActivityInputBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity : BaseActivity<ActivityInputBinding>() {
    override val layoutResourceId: Int = R.layout.activity_input
    private val viewModel: InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

}