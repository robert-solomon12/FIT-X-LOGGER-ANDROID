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

//    override fun update(empData: EmployeeModel) {
//        var foundEmpData: EmployeeModel? = empDatas.find { ed -> ed.id == empData.id }
//        if (foundEmpData != null) {
//            foundEmpData.fName = empData.fName
//            foundEmpData.sName = empData.sName
//            foundEmpData.dateOfB = empData.dateOfB
//            foundEmpData.email = empData.email
//            foundEmpData.ssNumber = empData.ssNumber
//            foundEmpData.nationality = empData.nationality
//            foundEmpData.jobTitle = empData.jobTitle
//        }
//    }

    fun logAll() {
        empDatas.forEach{ info("${it}") }
    }
}