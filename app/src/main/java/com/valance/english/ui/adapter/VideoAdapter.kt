package com.valance.english.ui.adapter


import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.valance.english.R

class VideoAdapter(private val videoList: List<Int>, private val onItemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<VideoAdapter.VideoViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(videoId: Int)
    }

    inner class VideoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnailImageView: ImageView = itemView.findViewById(R.id.thumbnailImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.video_element, parent, false)
        return VideoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val videoResourceId = videoList[position]
        val context = holder.itemView.context
        val uri = Uri.parse("android.resource://" + context.packageName + "/" + videoResourceId)

        Glide.with(context)
            .load(uri)
            .into(holder.thumbnailImageView)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(videoResourceId)
        }
    }

    override fun getItemCount(): Int {
        return videoList.size
    }
}




