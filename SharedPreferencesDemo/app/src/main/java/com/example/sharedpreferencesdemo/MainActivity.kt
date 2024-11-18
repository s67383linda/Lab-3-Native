package com.example.sharedpreferencesdemo

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var greetingTextView: TextView
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var cityEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var loadButton: Button
    private lateinit var clearButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize UI components
        greetingTextView = findViewById(R.id.tv_greeting)
        nameEditText = findViewById(R.id.et_name)
        ageEditText = findViewById(R.id.et_age)
        cityEditText = findViewById(R.id.et_city)
        saveButton = findViewById(R.id.btn_save)
        loadButton = findViewById(R.id.btn_load)
        clearButton = findViewById(R.id.btn_clear)

        // Set up button click listeners
        saveButton.setOnClickListener {
            saveData()
        }
        loadButton.setOnClickListener {
            loadData()
        }
        clearButton.setOnClickListener {
            clearData()
        }
    }

    private fun saveData() {
        // Validate inputs
        val name = nameEditText.text.toString()
        val age = ageEditText.text.toString()
        val city = cityEditText.text.toString()

        if (name.isEmpty() || age.isEmpty() || city.isEmpty()) {
            Toast.makeText(this, "All fields must be filled!", Toast.LENGTH_SHORT).show()
            return
        }

        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Open the editor to write to SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putString("userName", name)
        editor.putString("userAge", age)
        editor.putString("userCity", city)
        editor.apply()

        // Show a confirmation message
        greetingTextView.text = "Data saved!"
    }

    private fun loadData() {
        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Retrieve saved data
        val name = sharedPreferences.getString("userName", "No name saved")
        val age = sharedPreferences.getString("userAge", "No age saved")
        val city = sharedPreferences.getString("userCity", "No city saved")

        // Display the loaded data
        greetingTextView.text = "Welcome, $name!\nAge: $age\nCity: $city"
    }

    private fun clearData() {
        // Get the SharedPreferences object
        val sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        // Clear the saved data
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()

        // Clear the EditText fields
        nameEditText.text.clear()
        ageEditText.text.clear()
        cityEditText.text.clear()

        // Show a confirmation message
        greetingTextView.text = "Data cleared!"
    }
}
