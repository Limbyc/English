package com.valance.english.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valance.english.R
import com.valance.english.db.entity.Task

class TaskAdapter(private val context: Context) : RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    private var tasks: List<Task> = emptyList()
    private var onItemClickListener: OnItemClickListener? = null

    fun setTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleTextView: TextView = itemView.findViewById(R.id.appCompatTextView2)
        val countTextView: TextView = itemView.findViewById(R.id.appCompatTextView3)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = tasks[position]

        holder.titleTextView.text = "Задание ${position + 1}"
        holder.countTextView.text = "10 слов"

        holder.itemView.setOnClickListener {
            onItemClickListener?.onItemClick(task.id)
        }
    }

    // Метод для установки слушателя
    fun setOnItemClickListener(listener: OnItemClickListener) {
        this.onItemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(taskId: Int)
    }


    override fun getItemCount(): Int {
        return tasks.size
    }
}

