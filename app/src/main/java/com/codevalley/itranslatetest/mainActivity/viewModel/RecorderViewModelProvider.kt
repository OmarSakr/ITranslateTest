package com.codevalley.itranslatetest.mainActivity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codevalley.itranslatetest.mainActivity.repository.RecorderRepository

@Suppress("UNCHECKED_CAST")
class RecorderViewModelProvider(private val recorderRepository: RecorderRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecorderViewModel(recorderRepository) as T
    }
}