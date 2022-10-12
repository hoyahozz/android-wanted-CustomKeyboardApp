package com.preonboarding.customkeyboard.presentation.main

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.android.material.snackbar.Snackbar
import com.preonboarding.customkeyboard.presentation.R
import com.preonboarding.customkeyboard.presentation.common.base.BaseActivity
import com.preonboarding.customkeyboard.presentation.databinding.ActivityMainBinding
import com.preonboarding.customkeyboard.presentation.common.extension.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    override val layoutResourceId: Int = R.layout.activity_main
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.viewModel = viewModel

        setupDataBinding()
        initClipboard()
    }

    private fun setupDataBinding() {
        viewModel.localBookmarks.observe(this) {
            Toast.makeText(this, it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

    private fun initClipboard() {
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager

        clipboard.addPrimaryClipChangedListener {
            val copiedText = clipboard.primaryClip?.getItemAt(0)?.text.toString().trim()

            kotlin.runCatching {
                viewModel.saveBookmark(copiedText = copiedText)
            }
                .onSuccess {
                    binding.root.showSnackbar(
                        message = "$copiedText 저장 완료"
                    )
                }
                .onFailure {
                    binding.root.showSnackbar(
                        message = it.toString()
                    )
                }
        }
    }
}
