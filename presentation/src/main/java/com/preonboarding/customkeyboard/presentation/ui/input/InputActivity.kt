package com.preonboarding.customkeyboard.presentation.ui.input

import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.preonboarding.customkeyboard.domain.model.ToolbarMenu
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.databinding.ActivityInputBinding
import com.preonboarding.customkeyboard.presentation.ui.input.clipboard.ClipboardFragment
import com.preonboarding.customkeyboard.presentation.ui.input.keyboard.KeyboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InputActivity : BaseActivity<ActivityInputBinding>() {
    override val layoutResourceId: Int = R.layout.activity_input
    private val viewModel: InputViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        bindingView()
        initToolbar()
    }

    private fun bindingView() {
        viewModel.toolbarMenu.observe(this) {
            binding.layoutToolbar.toolbarMenu = it

            when(it) {
                ToolbarMenu.KEYBOARD -> {
                    replaceFragment(KeyboardFragment())
                }

                ToolbarMenu.CLIPBOARD -> {
                    replaceFragment(ClipboardFragment())
                }

                else -> {
                    replaceFragment(KeyboardFragment())
                }
            }
        }
    }

    private fun initToolbar() {

        binding.layoutToolbar.apply {
            layoutKeyboardTab.setOnClickListener {
                viewModel.changeMenu()
                changeFragment()
            }

            layoutClipboardTab.setOnClickListener {
                viewModel.changeMenu()
                changeFragment()
            }

        }
    }

    private fun changeFragment() {
        when(viewModel.toolbarMenu.value) {
            ToolbarMenu.KEYBOARD -> {
                replaceFragment(KeyboardFragment())
            }

            ToolbarMenu.CLIPBOARD -> {
                replaceFragment(ClipboardFragment())
            }

            else -> {
                replaceFragment(KeyboardFragment())
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fl_fragment_container, fragment)
            .commitAllowingStateLoss()
    }

}