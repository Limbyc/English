package com.valance.english.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valance.english.R

class TaskAdapter(private val context: Context, private val itemCount: Int) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.appCompatTextView2)
        val countTextView: TextView = itemView.findViewById(R.id.appCompatTextView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleTextView.text = "Задание ${position + 1}"
        holder.countTextView.text = "10 слов"
    }

    override fun getItemCount(): Int {
        return itemCount
    }
}
