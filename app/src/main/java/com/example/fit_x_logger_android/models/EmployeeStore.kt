package com.example.fit_x_logger_android.models

interface EmployeeStore {
    fun findAll(): List<EmployeeModel>
    fun create(empData: EmployeeModel)
   // fun update(empData: EmployeeModel)
}