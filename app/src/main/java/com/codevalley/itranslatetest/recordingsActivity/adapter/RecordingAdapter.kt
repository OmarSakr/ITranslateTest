package com.codevalley.itranslatetest.recordingsActivity.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codevalley.itranslatetest.databinding.ItemRecordingsBinding
import com.codevalley.itranslatetest.recordingsActivity.viewModel.RecordingViewModel
import java.io.File
import java.util.*

class RecordingAdapter(val context: Context, private val viewModelRecordings: RecordingViewModel) :
    RecyclerView.Adapter<RecordingAdapter.ViewHolder>() {
    var recordingsList: ArrayList<File>? = null
    var layoutInflater: LayoutInflater? = null


    init {
        recordingsList = ArrayList()
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRecordingsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tvtrackName.text = recordingsList!![position].name
        holder.binding.rlItem.setOnClickListener {
            viewModelRecordings.playRecording(context, recordingsList!![position].name)
        }
        holder.binding.ivDelete.setOnClickListener {
            recordingsList!![position].delete()
            recordingsList!!.removeAt(position)
            notifyItemRemoved(position)
            notifyItemMoved(position, recordingsList!!.size)
            notifyDataSetChanged()
            holder.binding.swipeLayout.close(true)
            viewModelRecordings.stopPlaying()
        }
    }

    override fun getItemCount(): Int {
        return recordingsList!!.size
    }

    fun addAll(data: List<File>?) {
        recordingsList!!.clear()
        recordingsList!!.addAll(data!!)
        notifyDataSetChanged()
    }

    class ViewHolder(var binding: ItemRecordingsBinding) : RecyclerView.ViewHolder(binding.root)


}