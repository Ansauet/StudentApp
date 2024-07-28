// File: UserService.kt
package com.example.studentapp

import retrofit2.Call
import retrofit2.http.GET

interface UserService {
    @GET("users")
    fun getUsers(): Call<List<ApiUser>>
}
