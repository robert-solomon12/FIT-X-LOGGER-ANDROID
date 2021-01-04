package com.example.fit_x_logger_android.models


/*
    Author Robert Solomon
    Student No.: 20079462
  */

interface EmployeeStore {
    fun findAll(): List<EmployeeModel>
    fun create(empData: EmployeeModel)
    fun update(empData: EmployeeModel)
    fun delete(empData: EmployeeModel)
}