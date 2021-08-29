package com.codevalley.itranslatetest.recordingsActivity.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codevalley.itranslatetest.recordingsActivity.repository.RecordingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


class RecordingViewModel(private val recordingRepository: RecordingRepository) : ViewModel() {
    var recordingsList: MutableLiveData<Array<out File>>? = null

    fun getRecordings() {
        viewModelScope.launch(Dispatchers.IO)
        {
            recordingsList = recordingRepository.getRecordings()
        }
    }

    fun playRecording(context: Context, title: String) =
        recordingRepository.playRecording(context, title)

    fun stopPlaying() = recordingRepository.stopPlaying()

}