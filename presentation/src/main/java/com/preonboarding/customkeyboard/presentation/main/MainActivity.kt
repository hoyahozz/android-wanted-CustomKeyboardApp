package com.preonboarding.customkeyboard.presentation.main

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setupDataBinding()
    }

    private fun setupDataBinding() {
        viewModel.localBookmarks.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }
}
