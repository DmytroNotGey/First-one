package com.example.pr3_4


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LessonsListActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var lessonAdapter: LessonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lessons_list)

        // Ініціалізуємо RecyclerView
        recyclerView = findViewById(R.id.recyclerViewLessons)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Створюємо список пар (для прикладу)
        val lessonsList = mutableListOf(
            Lesson(1, "Mathematics", "9:00", "10:30", "John Doe"),
            Lesson(2, "English", "11:00", "12:30", "Jane Smith"),
            Lesson(3, "Physics", "13:00", "14:30", "Alex Johnson")
        )

        // Ініціалізуємо адаптер
        lessonAdapter = LessonAdapter(lessonsList)

        // Встановлюємо адаптер для RecyclerView
        recyclerView.adapter = lessonAdapter

        val buttonAddLesson: Button = findViewById(R.id.buttonAddLesson)
        buttonAddLesson.setOnClickListener {
            // Відкрити попап для додавання нового заняття
            val dialog = AddLessonDialog { lesson ->
                lessonAdapter.addItem(lesson)
            }
            dialog.show(supportFragmentManager, "AddLessonDialog")
        }
    }
}
