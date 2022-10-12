package com.preonboarding.customkeyboard.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.preonboarding.customkeyboard.domain.model.Bookmark
import com.preonboarding.customkeyboard.domain.usecase.DeleteBookmarkUseCase
import com.preonboarding.customkeyboard.domain.usecase.GetBookmarkListUseCase
import com.preonboarding.customkeyboard.domain.usecase.SaveBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getBookmarkListUseCase: GetBookmarkListUseCase,
    private val saveBookmarkUseCase: SaveBookmarkUseCase,
    private val deleteBookmarkUseCase: DeleteBookmarkUseCase,
) : ViewModel() {

    private val _localBookmarks = MutableLiveData<List<Bookmark>>()
    val localBookmarks: LiveData<List<Bookmark>> get() = _localBookmarks

    fun getBookmarks() {
        viewModelScope.launch {
            _localBookmarks.postValue(getBookmarkListUseCase.invoke())
        }
    }

    fun saveBookmark() {
        viewModelScope.launch {
            saveBookmarkUseCase.invoke(Bookmark("String"))
        }
    }

    fun deleteBookmark() {
        viewModelScope.launch {
            deleteBookmarkUseCase.invoke(Bookmark("String"))
        }
    }
}