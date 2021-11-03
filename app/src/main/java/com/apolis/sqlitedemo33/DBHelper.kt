package com.apolis.sqlitedemo33

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class DBHelper(context: Context): SQLiteOpenHelper(context, "CompanyDB", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            db?.execSQL(CREATE_EMPLOYEE_TABLE)
            Log.i("DBHelper", "onCreate: Tables created successfully")
        } catch (sqlExp: SQLiteException) {
            sqlExp.printStackTrace()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    companion object {
        const val CREATE_EMPLOYEE_TABLE = """
            CREATE TABLE employee
            (
                empId INTEGER PRIMARY KEY AUTOINCREMENT, 
                name TEXT,
                emailId TEXT,
                phone TEXT
            )
        """
    }
}