package com.codevalley.itranslatetest.mainActivity.view

import android.Manifest
import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.codevalley.itranslatetest.databinding.ActivityMainBinding
import com.codevalley.itranslatetest.mainActivity.repository.RecorderRepository
import com.codevalley.itranslatetest.mainActivity.viewModel.RecorderViewModel
import com.codevalley.itranslatetest.mainActivity.viewModel.RecorderViewModelProvider
import com.codevalley.itranslatetest.recordingsActivity.view.RecordingsActivity


class MainActivity : AppCompatActivity() {
    private var record = false
    private lateinit var binding: ActivityMainBinding
    private var viewModelRecorder: RecorderViewModel? = null
    private lateinit var recorderRepository: RecorderRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initEventDriven()
    }

    private fun initEventDriven() {
        binding.ivMic.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    RECORD_AUDIO
                ) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                    this,
                    WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                val permissions = arrayOf(
                    RECORD_AUDIO,
                    WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                ActivityCompat.requestPermissions(this, permissions, 0)
            } else {
                recorderRepository = RecorderRepository(this)
                viewModelRecorder = ViewModelProvider(
                    this, RecorderViewModelProvider(recorderRepository)
                ).get(RecorderViewModel::class.java)
                if (!record) {
                    startRecording()
                } else if (record) {
                    stopRecording()
                }
            }
        }
        binding.buttonShowRecords.setOnClickListener {
            val intent = Intent(this, RecordingsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startRecording() {
        viewModelRecorder?.startRecording()
        record = true
        Toast.makeText(this, "Start recoding!", Toast.LENGTH_SHORT).show()
    }

    private fun stopRecording() {
        viewModelRecorder?.stopRecording()
        record = false
        Toast.makeText(this, "Stop recoding!", Toast.LENGTH_SHORT).show()
    }


}