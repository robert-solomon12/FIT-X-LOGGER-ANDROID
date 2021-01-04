package com.example.fit_x_logger_android.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

var lastId = 0L

/*
    Author Robert Solomon
    Student No.: 20079462
  */

internal fun getId(): Long {
    return lastId++
}

class EmployeeMemStore : EmployeeStore, AnkoLogger {

    val empDatas = ArrayList<EmployeeModel>()

    override fun findAll(): List<EmployeeModel> {
        return empDatas
    }

    override fun create(empData: EmployeeModel) {
        empData.id = getId()
        empDatas.add(empData)
        logAll()
    }

    override fun update(empData: EmployeeModel) {
        var foundEmpData: EmployeeModel? = empDatas.find { p -> p.id == empData.id }
        if (foundEmpData != null) {
            foundEmpData.name = empData.name
            foundEmpData.dateOfB = empData.dateOfB
            foundEmpData.email = empData.email
            foundEmpData.gender = empData.gender
            foundEmpData.ssNumber = empData.ssNumber
            foundEmpData.nationality = empData.nationality
            foundEmpData.jobTitle = empData.jobTitle
            foundEmpData.height = empData.height
            foundEmpData.weight = empData.weight
            foundEmpData.bmi = empData.bmi
            foundEmpData.result = empData.result
            foundEmpData.profilePic = empData.profilePic
            logAll()
        }
    }

    override fun delete(empData: EmployeeModel) {
        empDatas.remove(empData)
    }


   fun logAll() {
        empDatas.forEach { info("${it}") }
    }
}