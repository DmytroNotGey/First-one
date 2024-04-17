package com.example.pr3_4


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pr3_4.R
import com.example.pr3_4.Teacher

class TeachersListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var teacherAdapter: TeacherAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teachers_list)

        // Ініціалізуємо RecyclerView
        recyclerView = findViewById(R.id.recyclerViewTeachers)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Створюємо список викладачів (для прикладу)
        val teacherList = mutableListOf(
            Teacher(1, "John Doe", "Mathematics"),
            Teacher(2, "Jane Smith", "English"),
            Teacher(3, "Alex Johnson", "Physics")
        )

        // Ініціалізуємо адаптер
        teacherAdapter = TeacherAdapter(teacherList)

        // Встановлюємо адаптер для RecyclerView
        recyclerView.adapter = teacherAdapter

        val buttonAddTeacher: Button = findViewById(R.id.buttonAddTeacher)
        buttonAddTeacher.setOnClickListener {
            // Відкрити попап для додавання нового вчителя
            val dialog = AddTeacherDialog { teacher ->
                teacherAdapter.addItem(teacher)
            }
            dialog.show(supportFragmentManager, "AddTeacherDialog")
        }
    }
}
