package com.codevalley.itranslatetest.recordingsActivity.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codevalley.itranslatetest.recordingsActivity.repository.RecordingRepository

@Suppress("UNCHECKED_CAST")
class RecordingViewModelProvider(private val recordingRepository: RecordingRepository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RecordingViewModel(recordingRepository) as T
    }
}