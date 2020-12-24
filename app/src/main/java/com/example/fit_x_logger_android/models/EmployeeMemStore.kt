package com.example.fit_x_logger_android.models

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info



var lastId = 0

internal fun getId(): Int {
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
        var foundEmpData: EmployeeModel? = empDatas.find { ed -> ed.id == empData.id }
        if (foundEmpData != null) {
            foundEmpData.name = empData.name
            foundEmpData.dateOfB = empData.dateOfB
            foundEmpData.email = empData.email
            foundEmpData.gender = empData.gender
            foundEmpData.ssNumber = empData.ssNumber
            foundEmpData.nationality = empData.nationality
            foundEmpData.jobTitle = empData.jobTitle
            foundEmpData.profilePic = empData.profilePic
        }
    }

    internal fun logAll() {
        empDatas.forEach { info("${it}") }
    }
}