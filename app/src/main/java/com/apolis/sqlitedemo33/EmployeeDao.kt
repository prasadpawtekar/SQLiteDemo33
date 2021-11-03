package com.apolis.sqlitedemo33

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class EmployeeDao(context: Context) {

    val db: SQLiteDatabase = DBHelper(context).writableDatabase

    fun addEmployee(emp: Employee): Long {

        val values = ContentValues()
        values.put("name", emp.name)
        values.put("emailId", emp.emailId)
        values.put("phone", emp.phone)
        val empId: Long = db.insert("employee", null, values)

        return empId
    }

    fun deleteEmp(emp: Employee): Boolean {
        val n = db.delete("employee", "empId=?", arrayOf(emp.empId.toString()))
        return n==1
    }

    fun updateEmployee(emp:Employee): Boolean {
        val values = ContentValues()
        values.put("name", emp.name)
        values.put("emailId", emp.emailId)
        values.put("phone", emp.phone)

        val n = db.update("employee", values, "empId=?", arrayOf(emp.empId.toString()))
        return n==1
    }

    fun getEmployees(): ArrayList<Employee> {
        val employees = ArrayList<Employee>()

        val cursor: Cursor? = db.query("employee", null, null, null, null, null, "name")

        cursor?.let {
            while(it.moveToNext()) {
                val empId = it.getLong(0)
                val name = it.getString(1)
                val emailId = it.getString(2)
                val phone = it.getString(3)
                val emp = Employee(empId, name, emailId, phone)
                employees.add(emp)
            }
            it.close()
        }


        return employees
    }
}









