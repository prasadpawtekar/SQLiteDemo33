package com.apolis.sqlitedemo33

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_employees.*

class EmployeesActivity : AppCompatActivity() {
    private val RC_EDIT_EMPLOYEE: Int = 200
    lateinit var adapter: EmployeeAdapter
    lateinit var empDao: EmployeeDao
    lateinit var employees: ArrayList<Employee>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employees)
        empDao = EmployeeDao(this)
        employees = empDao.getEmployees()

        adapter = EmployeeAdapter(employees)
        lv_employees.adapter = adapter

        lv_employees.setOnItemClickListener { parent, view, position, id ->
            val selectedEmp = employees[position]
            val editEmpIntent = Intent(baseContext, UpdateEmployeeActivity::class.java)

            editEmpIntent.putExtra("empId", selectedEmp.empId)
            editEmpIntent.putExtra("name", selectedEmp.name)
            editEmpIntent.putExtra("emailId", selectedEmp.emailId)
            editEmpIntent.putExtra("phone", selectedEmp.phone)

            startActivityForResult(editEmpIntent, RC_EDIT_EMPLOYEE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_EDIT_EMPLOYEE) {
            if(resultCode == RESULT_OK) {
                employees = empDao.getEmployees()
                adapter = EmployeeAdapter(employees)
                lv_employees.adapter = adapter
            }
        }
    }
}