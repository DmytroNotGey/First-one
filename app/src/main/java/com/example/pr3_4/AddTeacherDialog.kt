package com.example.pr3_4

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment

class AddTeacherDialog(private val addTeacherListener: (Teacher) -> Unit) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.add_teacher_dialog, container, false)
        val editTextTeacherName = view.findViewById<EditText>(R.id.editTextName)
        val editTextSubject = view.findViewById<EditText>(R.id.editTextSubject)
        val buttonAdd = view.findViewById<Button>(R.id.buttonAdd)

        buttonAdd.setOnClickListener {
            val name = editTextTeacherName.text.toString()
            val subject = editTextSubject.text.toString()
            val teacher = Teacher(id, name, subject)

            addTeacherListener(teacher) // Передайте нового вчителя в вашу активність

            dismiss()
        }

        return view
    }
}
