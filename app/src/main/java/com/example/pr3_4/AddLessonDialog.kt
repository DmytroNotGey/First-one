package com.example.pr3_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddLessonDialog(private val addLessonListener: (Lesson) -> Unit) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_lesson_dialog, container, false)
        val editTextSubject = view.findViewById<EditText>(R.id.editTextSubject)
        val editTextStartTime = view.findViewById<EditText>(R.id.editTextStartTime)
        val editTextEndTime = view.findViewById<EditText>(R.id.editTextEndTime)
        val editTextTeacher = view.findViewById<EditText>(R.id.editTextTeacher)
        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val subject = editTextSubject.text.toString()
            val startTime = editTextStartTime.text.toString()
            val endTime = editTextEndTime.text.toString()
            val teacher = editTextTeacher.text.toString()

            val lesson = Lesson(0, subject, startTime, endTime, teacher)
            addLessonListener(lesson) // Передайте новий урок у вашу активність

            dismiss()
        }

        return view
    }
}
