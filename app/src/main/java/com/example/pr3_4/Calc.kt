package com.example.pr3_4

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Calc : AppCompatActivity() {

    private lateinit var textView: TextView
    private var input = StringBuilder()
    private var num1 = 0
    private var num2 = 0
    private var operator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc)

        textView = findViewById(R.id.textView)

        // Оголошення кнопок
        val button0 = findViewById<Button>(R.id.button0)
        val button1 = findViewById<Button>(R.id.button1)
        val button2 = findViewById<Button>(R.id.button2)
        val button3 = findViewById<Button>(R.id.button3)
        val button4 = findViewById<Button>(R.id.button4)
        val button5 = findViewById<Button>(R.id.button5)
        val button6 = findViewById<Button>(R.id.button6)
        val button7 = findViewById<Button>(R.id.button7)
        val button8 = findViewById<Button>(R.id.button8)
        val button9 = findViewById<Button>(R.id.button9)


        // Обробник для цифрових кнопок
        val numberListener = View.OnClickListener { v ->
            val b = v as Button
            input.append(b.text)
            textView.text = input.toString()
        }

        // Назначення обробника для цифрових кнопок
        button0.setOnClickListener(numberListener)
        button1.setOnClickListener(numberListener)
        button2.setOnClickListener(numberListener)
        button3.setOnClickListener(numberListener)
        button4.setOnClickListener(numberListener)
        button5.setOnClickListener(numberListener)
        button6.setOnClickListener(numberListener)
        button7.setOnClickListener(numberListener)
        button8.setOnClickListener(numberListener)
        button9.setOnClickListener(numberListener)

        // Додавання обробників для операційних кнопок (+, -, *, /)
        findViewById<Button>(R.id.buttonPlus).setOnClickListener {
            performOperation()
            operator = "+"
        }

        findViewById<Button>(R.id.buttonMinus).setOnClickListener {
            performOperation()
            operator = "-"
        }

        findViewById<Button>(R.id.buttonMultiply).setOnClickListener {
            performOperation()
            operator = "*"
        }

        findViewById<Button>(R.id.buttonDivide).setOnClickListener {
            performOperation()
            operator = "/"
        }

        // Додавання обробника для кнопки "="
        findViewById<Button>(R.id.buttonEquals).setOnClickListener {
            performOperation()
            operator = ""
        }

        // Додавання обробника для кнопки очищення
        findViewById<Button>(R.id.buttonClear).setOnClickListener {
            input.setLength(0)
            textView.text = ""
            num1 = 0
            num2 = 0
            operator = ""
        }

        // Додавання обробника для кнопки видалення останнього символу
        findViewById<Button>(R.id.buttonDelete).setOnClickListener {
            if (input.isNotEmpty()) {
                input.deleteCharAt(input.length - 1)
                textView.text = input.toString()
            }
        }
    }

    // Метод для виконання операцій
    private fun performOperation() {
        if (input.isNotEmpty()) {
            if (num1 == 0) {
                num1 = input.toString().toInt()
            } else {
                num2 = input.toString().toInt()
                when (operator) {
                    "+" -> num1 += num2
                    "-" -> num1 -= num2
                    "*" -> num1 *= num2
                    "/" -> {
                        if (num2 != 0) {
                            num1 /= num2
                        } else {
                            // Обробка помилки ділення на нуль
                            textView.text = "Error"
                            return
                        }
                    }
                }
            }
            textView.text = num1.toString()
            input.setLength(0)
        }
    }
}
