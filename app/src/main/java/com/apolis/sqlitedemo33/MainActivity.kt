package com.apolis.sqlitedemo33

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var empDao: EmployeeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        empDao = EmployeeDao(baseContext)
        btn_add_emp.setOnClickListener {
            addEmployee()
        }

        btn_show_employees.setOnClickListener {
            val eIntent = Intent(baseContext, EmployeesActivity::class.java)
            startActivity(eIntent)
        }
    }

    fun addEmployee() {
        val name = et_name.text.toString()
        val email = et_email_id.text.toString()
        val phone = et_phone.text.toString()
        val emp = Employee(0, name, email, phone)

        val insertId = empDao.addEmployee(emp)
        if(insertId > 0) {
            Toast.makeText(baseContext, "Employee added with id : $insertId", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(baseContext, "Failed to add employee. Please retry.", Toast.LENGTH_LONG).show()
        }
    }
}