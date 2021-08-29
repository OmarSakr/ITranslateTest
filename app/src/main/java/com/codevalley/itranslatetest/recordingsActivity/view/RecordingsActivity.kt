package com.codevalley.itranslatetest.recordingsActivity.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itranslatetest.databinding.ActivityRecordingsBinding
import com.codevalley.itranslatetest.recordingsActivity.adapter.RecordingAdapter
import com.codevalley.itranslatetest.recordingsActivity.repository.RecordingRepository
import com.codevalley.itranslatetest.recordingsActivity.viewModel.RecordingViewModel
import com.codevalley.itranslatetest.recordingsActivity.viewModel.RecordingViewModelProvider
import java.io.File

class RecordingsActivity : AppCompatActivity() {
    private lateinit var recordingAdapter: RecordingAdapter
    private lateinit var binding: ActivityRecordingsBinding
    private lateinit var viewModelRecordings: RecordingViewModel
    private var data: ArrayList<File>? = ArrayList()
    private lateinit var recordingRepository: RecordingRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordingsBinding.inflate(layoutInflater)
        val view: View = binding.root
        setContentView(view)
        initUi()
        initEventDriven()
    }

    private fun initEventDriven() {
        getExternalFilesDir("audio")!!.absolutePath
    }

    private fun initUi() {
        recordingRepository = RecordingRepository(this)
        viewModelRecordings = ViewModelProvider(
            this, RecordingViewModelProvider(recordingRepository)
        ).get(RecordingViewModel::class.java)
        viewModelRecordings.getRecordings()
        lifecycleScope.launchWhenStarted {
            viewModelRecordings.recordingsList!!.observe(this@RecordingsActivity, Observer {
                if (it != null) {
                    for (i in it) {
                        data!!.add(i)
                    }
                    recordingAdapter.addAll(data)
                    recordingAdapter.notifyDataSetChanged()
                }
            })
        }
        initRecycler()
    }

    private fun initRecycler() {
        val linearLayoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        recordingAdapter = RecordingAdapter(this, viewModelRecordings)
        binding.rvRecordings.layoutManager = linearLayoutManager
        binding.rvRecordings.adapter = recordingAdapter
    }
}