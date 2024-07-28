package com.example.studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
class AddUserActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextWebsite: EditText
    private lateinit var buttonSave: Button
    private lateinit var dbHelper: DatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)

        dbHelper = DatabaseHelper(this)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextWebsite = findViewById(R.id.editTextWebsite)
        buttonSave = findViewById(R.id.buttonSave)

        buttonSave.setOnClickListener {
            addUser()
        }
    }

    private fun addUser() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextPhone.text.toString()
        val website = editTextWebsite.text.toString()

        val newUser = ApiUser(name = name, email = email, phone = phone, website = website)
        dbHelper.upsertUser(newUser)
        setResult(Activity.RESULT_OK)
        finish()
    }
}
