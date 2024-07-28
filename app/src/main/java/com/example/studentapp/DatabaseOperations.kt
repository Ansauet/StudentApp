// DatabaseOperations.kt
package com.example.studentapp

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

fun insertUser(dbHelper: DatabaseHelper, user: User1) {
    val db: SQLiteDatabase = dbHelper.writableDatabase
    val contentValues = ContentValues().apply {
        put(DatabaseHelper.COLUMN_NAME, user.name)
        put(DatabaseHelper.COLUMN_EMAIL, user.email)
        put(DatabaseHelper.COLUMN_PHONE, user.phone)
        put(DatabaseHelper.COLUMN_WEBSITE, user.website)
    }
    db.insert(DatabaseHelper.TABLE_NAME, null, contentValues)
    db.close()
}
