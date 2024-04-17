package com.example.pr3_4

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pr3_4.R

class MenuActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.second_layout)

        val editText: EditText = findViewById(R.id.editText)
        val buttonLogout = findViewById<Button>(R.id.buttonLogout)

        val buttonTeachers = findViewById<Button>(R.id.buttonTeachers)
        val buttonLessons = findViewById<Button>(R.id.buttonLessons)

        // Ініціалізація SharedPreferences
        sharedPreferences = getSharedPreferences(Const.MY_PREFS, Context.MODE_PRIVATE)

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                if (s.toString() == Const.CALC) {
                    val intent = Intent(this@MenuActivity, Calc::class.java)
                    startActivity(intent)
                }
            }
        })

        buttonLogout.setOnClickListener {
            sharedPreferences.edit().putBoolean(Const.IS_AUTH, false).apply()
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonTeachers.setOnClickListener {
            // Перехід на екран зі списком викладачів
            val intent = Intent(this, TeachersListActivity::class.java)
            startActivity(intent)
        }

        buttonLessons.setOnClickListener {
            // Перехід на екран зі списком пар
            val intent = Intent(this, LessonsListActivity::class.java)
            startActivity(intent)
        }
    }
}
