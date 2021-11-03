package com.apolis.sqlitedemo33

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_update_employee.*

class UpdateEmployeeActivity : AppCompatActivity() {
    lateinit var selectedEmployee: Employee
    lateinit var empDao: EmployeeDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_employee)

        empDao = EmployeeDao(this)
        intent.extras?.let{
            val empId = it.getLong("empId")
            val name = it.getString("name")?: ""
            val emailId = it.getString("emailId") ?: ""
            val phone = it.getString("phone") ?: ""

            selectedEmployee = Employee(empId, name, emailId, phone)

            et_name.setText(selectedEmployee.name)
            et_email_id.setText(selectedEmployee.emailId)
            et_phone.setText(selectedEmployee.phone)
        }

        setupEvents()

    }

    private fun setupEvents() {

        btn_update.setOnClickListener {
            updateEmployee()
        }

        btn_delete.setOnClickListener {
            confirmDeleteAction()
        }
    }

    private fun confirmDeleteAction() {

        val confirmDialog = AlertDialog.Builder(this)
            .setTitle("Confirm Action")
            .setMessage("Are you sure you want to delete this employee information?")
            .setPositiveButton("Yes") { dialog, which ->   deleteEmployee()}
            .setNegativeButton("No") { dialog, which -> }
            .create()

        confirmDialog.show()
    }

    private fun deleteEmployee() {
        val deleted = empDao.deleteEmp(selectedEmployee)
        if(deleted) {
            Toast.makeText(baseContext, "Employee information deleted successfully", Toast.LENGTH_LONG).show()

            setResult(RESULT_OK)
            finish()
        } else {
            Toast.makeText(baseContext, "Failed to delete Employee information. Please retry.", Toast.LENGTH_LONG).show()
        }
    }

    private fun updateEmployee() {
        selectedEmployee.name = et_name.text.toString()
        selectedEmployee.emailId = et_email_id.text.toString()
        selectedEmployee.phone = et_phone.text.toString()

        val updated = empDao.updateEmployee(selectedEmployee)
        if(updated ) {
            Toast.makeText(baseContext, "Employee info updated successfully", Toast.LENGTH_LONG).show()
            setResult(RESULT_OK)
            finish()
        } else {
            Toast.makeText(baseContext, "Failed to update Employee info. Please retry.", Toast.LENGTH_LONG).show()

        }
    }
}