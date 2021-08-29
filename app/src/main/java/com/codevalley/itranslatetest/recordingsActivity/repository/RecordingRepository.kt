package com.codevalley.itranslatetest.recordingsActivity.repository

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.net.Uri
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import java.io.File

class RecordingRepository(val context: Context) {
    var mediaPlayer: MediaPlayer = MediaPlayer()

    fun playRecording(context: Context, title: String) {
        val path =
            Uri.parse(context.externalCacheDir?.absolutePath + "/soundrecorder/$title")

        val manager: AudioManager =
            context.getSystemService(Context.AUDIO_SERVICE) as AudioManager

        if (manager.isMusicActive) {
            Toast.makeText(
                context,
                "Another recording is just playing! Wait until it's finished!",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            mediaPlayer = MediaPlayer().apply {
                setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
                )
                setDataSource(context, path)
                prepare()
                start()
            }
        }

    }

    fun stopPlaying() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    private val recorderDirectory =
        File(context.externalCacheDir?.absolutePath + "/soundrecorder/")

    var recordingsData: MutableLiveData<Array<out File>>? = MutableLiveData<Array<out File>>()

    init {
        getRecording()
    }

    private fun getRecording() {
        val files: Array<out File>? = recorderDirectory.listFiles()
        recordingsData!!.value = files
    }

    fun getRecordings() = recordingsData

}
