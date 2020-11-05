package com.example.a7minutesworkout

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.prefs.PreferencesFactory

class sqliteOpenHelper(context: Context,factory: SQLiteDatabase.CursorFactory?): SQLiteOpenHelper(context, DATABASE_NAME,factory, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1 // This DATABASE Version
        private const val DATABASE_NAME = "SevenMinutesWorkout.db" // Name of the DATABASE
        private const val TABLE_HISTORY = "history" // Table Name
        private const val COLUMN_ID = "_id" // Column Id
        private const val COLUMN_COMPLETED_DATE = "completed_date" // Column for Completed Date
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_HISTORY_TABLE = ("CREATE TABLE " +
                TABLE_HISTORY + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_COMPLETED_DATE
                + " TEXT)") // Create History Table Query.
        db!!.execSQL(CREATE_HISTORY_TABLE) // Executing the create table query.
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_HISTORY") // It drops the existing history table
        onCreate(db)
    }

    fun addDate(date: String) {
        val values = ContentValues()
        values.put(COLUMN_COMPLETED_DATE, date)
        val db = this.writableDatabase
        db.insert(TABLE_HISTORY, null, values)
        db.close() // Database is closed after insertion.
    }

    fun getAllCompletedDatesList(): ArrayList<String> {
        val list = ArrayList<String>() // ArrayList is initialized
        val db = this.readableDatabase // Create and/or open a database that will be used for reading and writing.
        //  Runs the provided SQL and returns a Cursor over the result set.
        // Query for selecting all the data from history table.
        val cursor = db.rawQuery("SELECT * FROM $TABLE_HISTORY", null)

        // Move the cursor to the next row.
        while (cursor.moveToNext()) {
            // Returns the zero-based index for the given column name, or -1 if the column doesn't exist.
            list.add(cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETED_DATE))) // value is added in the list
        }
        cursor.close() // Cursor is closed after its used.
        return list // List is returned.
    }
}