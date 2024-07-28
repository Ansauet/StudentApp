package com.example.studentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var apiUserAdapter: ApiUserAdapter
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var addUserButton: Button

    companion object {
        private const val ADD_USER_REQUEST_CODE = 1
        private const val UPDATE_USER_REQUEST_CODE = 2 // Add a request code for updates
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        apiUserAdapter = ApiUserAdapter(emptyList(), dbHelper) { user ->
            val intent = Intent(this, UpdateUserActivity::class.java)
            intent.putExtra("USER", user)
            startActivityForResult(intent, UPDATE_USER_REQUEST_CODE) // Use startActivityForResult
        }
        recyclerView.adapter = apiUserAdapter

        addUserButton = findViewById(R.id.buttonAddUser)
        addUserButton.setOnClickListener {
            val intent = Intent(this, AddUserActivity::class.java)
            startActivityForResult(intent, ADD_USER_REQUEST_CODE)
        }

        refreshRecyclerView()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == ADD_USER_REQUEST_CODE || requestCode == UPDATE_USER_REQUEST_CODE) {
                refreshRecyclerView()
            }
        }
    }

    private fun refreshRecyclerView() {
        val users = dbHelper.getAllUsers()
        apiUserAdapter.updateUserList(users)
        if (users.isEmpty()) {
            fetchDataAndStoreInDatabase()
        }
    }

    private fun fetchDataAndStoreInDatabase() {
        // Make API call using Retrofit (replace with your actual API call)
        RetrofitClient.userService.getUsers().enqueue(object : Callback<List<ApiUser>> {
            override fun onResponse(call: Call<List<ApiUser>>, response: Response<List<ApiUser>>) {
                if (response.isSuccessful) {
                    val users = response.body() ?: emptyList()
                    users.forEach { user ->
                        dbHelper.upsertUser(user)
                    }
                    refreshRecyclerView()
                } else {
                    // Handle API error
                    Toast.makeText(this@MainActivity, "API Error", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<List<ApiUser>>, t: Throwable) {
                // Handle network error
                Toast.makeText(this@MainActivity, "Network Error", Toast.LENGTH_SHORT).show()
            }
        })
    }
}