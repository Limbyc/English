package com.valance.english.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.valance.english.R
import com.valance.english.db.entity.Courses

class CourseAdapter(private var courses: List<Courses>) :
    RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val courseNameTextView: TextView = itemView.findViewById(R.id.courseNameTextView)
        val courseDescriptionTextView: TextView = itemView.findViewById(R.id.courseDescriptionTextView)
        val levelEnglishTextView: TextView = itemView.findViewById(R.id.levelEnglish)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.courses_element, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentCourse = courses[position]
        holder.courseNameTextView.text = currentCourse.name
        holder.courseDescriptionTextView.text = "Cost: ${currentCourse.cost} $"
        holder.levelEnglishTextView.text = currentCourse.desc
    }

    override fun getItemCount() = courses.size

    fun updateCourses(courses: List<Courses>) {
        this.courses = courses
        notifyDataSetChanged()
        Log.d("CourseAdapter", "Received ${courses.size} courses from the database")
    }


}
