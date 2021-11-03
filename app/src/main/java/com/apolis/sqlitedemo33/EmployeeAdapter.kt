package com.apolis.sqlitedemo33

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class EmployeeAdapter(val employees: ArrayList<Employee>): BaseAdapter() {
    override fun getCount() = employees.size

    override fun getItem(position: Int) = employees[position]

    override fun getItemId(position: Int) = getItem(position).empId

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if(view == null) {
            val layoutInflater = LayoutInflater.from(parent!!.context)
            view = layoutInflater.inflate(R.layout.item_employee, null)
        }

        view?.let {
            val tv_name: TextView = it.findViewById(R.id.tv_name);
            val tv_email: TextView = it.findViewById(R.id.tv_email)
            val tv_phone: TextView = it.findViewById(R.id.tv_phone)

            val emp = getItem(position)
            tv_name.text = emp.name
            tv_email.text = emp.emailId
            tv_phone.text = emp.phone

        }


        return view!!
    }
}