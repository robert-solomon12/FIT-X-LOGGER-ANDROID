package com.example.fit_x_logger_android.models

import android.content.Context
import com.example.fit_x_logger_android.helpers.exists
import com.example.fit_x_logger_android.helpers.read
import com.example.fit_x_logger_android.helpers.write
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import org.jetbrains.anko.AnkoLogger
import java.util.*

val JSON_FILE = "Employee DataList.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<ArrayList<EmployeeModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class EmployeeJSONStore : EmployeeStore, AnkoLogger {

    val context: Context
    var empDatas = mutableListOf<EmployeeModel>()

    constructor (context: Context) {
        this.context = context
        if (exists(context, JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<EmployeeModel> {
        return empDatas
    }

    override fun create(empData: EmployeeModel) {
        empData.id =  generateRandomId()
        empDatas.add(empData)
        serialize()
    }

    override fun update(empData: EmployeeModel) {
        val employeeDataList = findAll() as ArrayList<EmployeeModel>
        var foundEmpData: EmployeeModel? = employeeDataList.find { p -> p.id == empData.id }
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
        serialize()
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(empDatas, listType)
        write(context, JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(context, JSON_FILE)
        empDatas = Gson().fromJson(jsonString, listType)
    }
}