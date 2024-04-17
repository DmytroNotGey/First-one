package com.example.pr3_4

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.pr3_4.R

class RegistrationActivity : AppCompatActivity() {
    private lateinit var imageViewAvatar: ImageView
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)
        val editTextName = findViewById<EditText>(R.id.editTextName)
        val editTextPhone = findViewById<EditText>(R.id.editTextPhone)
        val editTextDateOfBirth = findViewById<EditText>(R.id.editTextDateOfBirth)
        val editTextLogin = findViewById<EditText>(R.id.editTextLogin)
        val editTextPassword = findViewById<EditText>(R.id.editTextPassword)
        val editTextConfirmPassword = findViewById<EditText>(R.id.editTextConfirmPassword)
        val buttonRegister = findViewById<Button>(R.id.buttonRegister)

        buttonRegister.setOnClickListener {
            val name = editTextName.text.toString()
            val phone = editTextPhone.text.toString()
            val dob = editTextDateOfBirth.text.toString()
            val login = editTextLogin.text.toString()
            val password = editTextPassword.text.toString()
            val confirmPassword = editTextConfirmPassword.text.toString()

            if (password == confirmPassword) {
                val editor = sharedPreferences.edit()
                editor.putString("name", name)
                editor.putString("phone", phone)
                editor.putString("dob", dob)
                editor.putString("login", login)
                editor.putString("password", password)
                editor.apply()

                // Перехід на сторінку авторизації після успішної реєстрації
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                // Показуємо повідомлення користувачу про невідповідність паролів
                Toast.makeText(this, "Паролі не збігаються!", Toast.LENGTH_SHORT).show()
            }
        }
        imageViewAvatar = findViewById(R.id.imageViewAvatar)
        imageViewAvatar.setOnClickListener {
            chooseImageFromGallery()
        }

    }
    private fun chooseImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        resultLauncher.launch(intent)
    }

    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data: Intent? = result.data
            val selectedImageUri = data?.data
            imageViewAvatar.setImageURI(selectedImageUri)
        }
    }

}