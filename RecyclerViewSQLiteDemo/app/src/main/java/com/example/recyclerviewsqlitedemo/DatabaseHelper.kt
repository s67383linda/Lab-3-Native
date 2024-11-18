package com.example.recyclerviewsqlitedemo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "UserDatabase"
        private const val DATABASE_VERSION = 1
        private const val TABLE_USERS = "Users"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_AGE = "age"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = ("CREATE TABLE $TABLE_USERS ("
                + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "$COLUMN_NAME TEXT,"
                + "$COLUMN_AGE INTEGER)")
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(name: String, age: Int): Boolean {
        val db = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_AGE, age)
        }
        val result = db.insert(TABLE_USERS, null, contentValues)
        db.close()
        return result != -1L
    }

    fun getAllUsers(): List<User> {
        val userList = ArrayList<User>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_USERS", null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
                val name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME))
                val age = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_AGE))
                userList.add(User(id, name, age))
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return userList
    }
}
