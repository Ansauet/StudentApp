package com.example.studentapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import android.app.Activity
class UpdateUserActivity : AppCompatActivity() {

    private lateinit var editTextName: EditText
    private lateinit var editTextEmail: EditText
    private lateinit var editTextPhone: EditText
    private lateinit var editTextWebsite: EditText
    private lateinit var buttonUpdate: Button
    private lateinit var dbHelper: DatabaseHelper
    private var userId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_user)

        dbHelper = DatabaseHelper(this)

        editTextName = findViewById(R.id.editTextName)
        editTextEmail = findViewById(R.id.editTextEmail)
        editTextPhone = findViewById(R.id.editTextPhone)
        editTextWebsite = findViewById(R.id.editTextWebsite)
        buttonUpdate = findViewById(R.id.buttonUpdate)

        val user = intent.getParcelableExtra<ApiUser>("USER")
        if (user != null) {
            userId = user.id
            editTextName.setText(user.name)
            editTextEmail.setText(user.email)
            editTextPhone.setText(user.phone)
            editTextWebsite.setText(user.website)
        }

        buttonUpdate.setOnClickListener {
            updateUser()
        }
    }

    private fun updateUser() {
        val name = editTextName.text.toString()
        val email = editTextEmail.text.toString()
        val phone = editTextPhone.text.toString()
        val website = editTextWebsite.text.toString()

        val updatedUser = ApiUser(id = userId, name = name, email = email, phone = phone, website = website)
        dbHelper.upsertUser(updatedUser)
        setResult(Activity.RESULT_OK)
        finish()
    }
}
