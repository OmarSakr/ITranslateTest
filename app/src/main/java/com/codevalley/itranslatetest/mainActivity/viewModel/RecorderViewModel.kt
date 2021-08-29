package com.codevalley.itranslatetest.mainActivity.viewModel

import androidx.lifecycle.ViewModel
import com.codevalley.itranslatetest.mainActivity.repository.RecorderRepository


class RecorderViewModel(private val recorderRepository: RecorderRepository) : ViewModel() {
    fun startRecording() = recorderRepository.startRecording()

    fun stopRecording() = recorderRepository.stopRecording()


}