package com.preonboarding.customkeyboard.presentation.main

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.databinding.ActivityMainBinding
import com.preonboarding.customkeyboard.presentation.clipboard.ClipboardActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel
        binding.tvGet.setOnClickListener {
            startActivity(Intent(this, ClipboardActivity::class.java))
        }
    }


}
