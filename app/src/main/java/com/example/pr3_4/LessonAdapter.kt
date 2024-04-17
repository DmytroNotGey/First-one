package com.example.pr3_4

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pr3_4.R
import com.example.pr3_4.Lesson

class LessonAdapter(private val lessonsList: MutableList<Lesson>) : RecyclerView.Adapter<LessonAdapter.LessonViewHolder>() {

    // ViewHolder для елементів списку
    class LessonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val subjectTextView: TextView = itemView.findViewById(R.id.textViewSubject)
        val startTimeTextView: TextView = itemView.findViewById(R.id.textViewStartTime)
        val endTimeTextView: TextView = itemView.findViewById(R.id.textViewEndTime)
        val teacherTextView: TextView = itemView.findViewById(R.id.textViewTeacher)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)
    }

    // Створення нового елемента списку (ViewHolder)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false)
        return LessonViewHolder(itemView)
    }

    // Заповнення елемента списку даними
    override fun onBindViewHolder(holder: LessonViewHolder, position: Int) {
        val currentItem = lessonsList[position]
        holder.subjectTextView.text = currentItem.subject
        holder.startTimeTextView.text = currentItem.startTime
        holder.endTimeTextView.text = currentItem.endTime
        holder.teacherTextView.text = currentItem.teacher

        holder.deleteButton.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION) { // Перевірка на правильність позиції
                lessonsList.removeAt(holder.adapterPosition)
                notifyItemRemoved(holder.adapterPosition)
            }
        }
        holder.itemView.setOnClickListener {
            showEditLessonDialog(currentItem, position, holder.itemView.context)
        }
    }
    fun updateLesson(position: Int, updatedLesson: Lesson) {
        lessonsList[position] = updatedLesson
        notifyDataSetChanged()
    }

    fun showEditLessonDialog(lesson: Lesson, position: Int, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Редагувати")

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_lesson, null)
        builder.setView(view)

        val editTextSubject = view.findViewById<EditText>(R.id.editTextSubject)
        val editTextStartTime = view.findViewById<EditText>(R.id.editTextStartTime)
        val editTextEndTime = view.findViewById<EditText>(R.id.editTextEndTime)
        val editTextTeacher = view.findViewById<EditText>(R.id.editTextTeacher)

        editTextSubject.setText(lesson.subject)
        editTextStartTime.setText(lesson.startTime)
        editTextEndTime.setText(lesson.endTime)
        editTextTeacher.setText(lesson.teacher)

        builder.setPositiveButton("Оновити") { dialog, _ ->
            val subject = editTextSubject.text.toString()
            val startTime = editTextStartTime.text.toString()
            val endTime = editTextEndTime.text.toString()
            val teacher = editTextTeacher.text.toString()

            if (subject.isNotEmpty() && startTime.isNotEmpty() && endTime.isNotEmpty() && teacher.isNotEmpty()) {
                val updatedLesson = Lesson(lesson.id, subject, startTime, endTime, teacher)
                updateLesson(position, updatedLesson)
            } else {
                Toast.makeText(context, "Заповніть всі поля", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNegativeButton("Скасувати") { dialog, _ ->
            dialog.dismiss()
        }

        builder.create().show()
    }
    // Повернення кількості елементів у списку
    override fun getItemCount(): Int {
        return lessonsList.size
    }

    fun addItem(lesson: Lesson) {
        lessonsList.add(lesson)
        notifyDataSetChanged()
    }
}
