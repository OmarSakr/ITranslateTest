package com.codevalley.itranslatetest.mainActivity.repository

import android.content.Context
import android.media.MediaRecorder
import java.io.File
import java.io.IOException


class RecorderRepository(var context: Context) {

    private var output: String? = null
    private var mediaRecorder: MediaRecorder? = null
    private val dir: File =
        File(context.externalCacheDir?.absolutePath + "/soundrecorder/")


    init {
        try {
            // create a File object for the parent directory
            val recorderDirectory =
                File(context.externalCacheDir?.absolutePath + "/soundrecorder/")
            // have the object build the directory structure, if needed.
            recorderDirectory.mkdirs()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        if (dir.exists()) {
            if (dir.listFiles().isNotEmpty()) {
                var lastIndex: Int =
                    dir.listFiles()[dir.listFiles().size - 1].name.substring(9, 10).toInt()
                lastIndex += 1
                output =
                    context.externalCacheDir?.absolutePath + "/soundrecorder/recording" + lastIndex + ".mp3"

            } else {
                val count = dir.listFiles().size
                output =
                    context.externalCacheDir?.absolutePath + "/soundrecorder/recording" + count + ".mp3"
            }
        }
        mediaRecorder = MediaRecorder()
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }

    fun startRecording() {
        try {
            mediaRecorder?.prepare()
            mediaRecorder?.start()
        } catch (e: IllegalStateException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun stopRecording() {
        mediaRecorder?.stop()
        mediaRecorder?.release()
        initRecorder()
    }


    private fun initRecorder() {
        mediaRecorder = MediaRecorder()
        if (dir.exists()) {
            if (dir.listFiles().isNotEmpty()) {
                var lastIndex: Int =
                    dir.listFiles()[dir.listFiles().size - 1].name.substring(9, 10).toInt()
                lastIndex += 1
                output =
                    context.externalCacheDir?.absolutePath + "/soundrecorder/recording" + lastIndex + ".mp3"

            } else {
                val count = dir.listFiles().size
                output =
                    context.externalCacheDir?.absolutePath + "/soundrecorder/recording" + count + ".mp3"
            }
        }
        mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
        mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
        mediaRecorder?.setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
        mediaRecorder?.setOutputFile(output)
    }


}