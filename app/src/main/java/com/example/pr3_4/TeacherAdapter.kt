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
import com.example.pr3_4.Teacher

class TeacherAdapter(private val teacherList: MutableList<Teacher>) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    // ViewHolder для елементів списку
    class TeacherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.textViewName)
        val subjectTextView: TextView = itemView.findViewById(R.id.textViewSubject)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)
    }

    // Створення нового елемента списку (ViewHolder)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_theacher, parent, false)
        return TeacherViewHolder(itemView)
    }

    // Заповнення елемента списку даними
    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val currentItem = teacherList[position]
        holder.nameTextView.text = currentItem.name
        holder.subjectTextView.text = currentItem.subject

        holder.deleteButton.setOnClickListener {
            teacherList.removeAt(position)
            notifyDataSetChanged()
        }
        holder.itemView.setOnClickListener {
            showEditTeacherDialog(currentItem, position, holder.itemView.context)
        }

    }
    fun updateTeacher(position: Int, updatedTeacher: Teacher) {
        teacherList[position] = updatedTeacher
        notifyDataSetChanged()
    }
    private fun showEditTeacherDialog(teacher: Teacher, position: Int, context: Context) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Редагувати")

        val view = LayoutInflater.from(context).inflate(R.layout.dialog_edit_teacher, null)
        builder.setView(view)

        val editTextName = view.findViewById<EditText>(R.id.editTextName)
        val editTextSubject = view.findViewById<EditText>(R.id.editTextSubject)

        editTextName.setText(teacher.name)
        editTextSubject.setText(teacher.subject)

        builder.setPositiveButton("Оновити") { dialog, _ ->
            val name = editTextName.text.toString()
            val subject = editTextSubject.text.toString()

            if (name.isNotEmpty() && subject.isNotEmpty()) {
                val updatedTeacher = Teacher(teacher.id, name, subject)
                updateTeacher(position, updatedTeacher) // Передача правильної позиції в метод updateTeacher
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
        return teacherList.size
    }

    // Додавання нового викладача до списку і оновлення адаптера
    fun addItem(teacher: Teacher) {
        teacherList.add(teacher)
        notifyDataSetChanged()
    }
}
