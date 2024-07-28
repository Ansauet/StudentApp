package com.example.studentapp

import android.provider.BaseColumns

object UserContract {
    object UserEntry : BaseColumns {
        const val TABLE_NAME = "user"
        const val COLUMN_NAME_NAME = "name"
        const val COLUMN_NAME_EMAIL = "email"
        const val COLUMN_NAME_STREET = "street"
        const val COLUMN_NAME_CITY = "city"
        const val COLUMN_NAME_ZIPCODE = "zipcode"
        const val COLUMN_NAME_PHONE = "phone"
        const val COLUMN_NAME_WEBSITE = "website"

        const val SQL_CREATE_TABLE = """
            CREATE TABLE $TABLE_NAME (
                ${BaseColumns._ID} INTEGER PRIMARY KEY,
                $COLUMN_NAME_NAME TEXT,
                $COLUMN_NAME_EMAIL TEXT,
                $COLUMN_NAME_STREET TEXT,
                $COLUMN_NAME_CITY TEXT,
                $COLUMN_NAME_ZIPCODE TEXT,
                $COLUMN_NAME_PHONE TEXT,
                $COLUMN_NAME_WEBSITE TEXT
            )
        """

        const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"
    }
}
