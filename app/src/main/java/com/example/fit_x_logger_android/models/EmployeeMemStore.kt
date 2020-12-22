package com.example.fit_x_logger_android.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class EmployeeMemStore : EmployeeStore, AnkoLogger {

    val empDatas = ArrayList<EmployeeModel>()

    override fun findAll(): List<EmployeeModel> {
        return empDatas
    }

    override fun create(empData: EmployeeModel) {
        empDatas.add(empData)
    }

    fun logAll() {
        empDatas.forEach{ info("${it}") }
    }
}