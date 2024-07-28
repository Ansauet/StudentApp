package com.example.studentapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ApiUserAdapter(
    private var userList: List<ApiUser>,
    private val dbHelper: DatabaseHelper,
    private val onUpdateClick: (ApiUser) -> Unit
) : RecyclerView.Adapter<ApiUserAdapter.ApiUserViewHolder>() {

    class ApiUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val userName: TextView = itemView.findViewById(R.id.userName)
        val userEmail: TextView = itemView.findViewById(R.id.userEmail)
        val userPhone: TextView = itemView.findViewById(R.id.userPhone)
        val userWebsite: TextView = itemView.findViewById(R.id.userWebsite)
        val deleteButton: Button = itemView.findViewById(R.id.buttonDelete)
        val updateButton: Button = itemView.findViewById(R.id.buttonUpdate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_item, parent, false)
        return ApiUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ApiUserViewHolder, position: Int) {
        val user = userList[position]
        holder.userName.text = user.name
        holder.userEmail.text = user.email
        holder.userPhone.text = user.phone
        holder.userWebsite.text = user.website

        // Delete button click listener
        holder.deleteButton.setOnClickListener {
            deleteUser(user)
        }

        // Update button click listener
        holder.updateButton.setOnClickListener {
            onUpdateClick(user)
        }
    }

    override fun getItemCount() = userList.size

    fun updateUserList(newUsers: List<ApiUser>) {
        userList = newUsers
        notifyDataSetChanged()
    }

    private fun deleteUser(user: ApiUser) {
        dbHelper.deleteUser(user.id)
        updateUserList(dbHelper.getAllUsers())
    }
}
