package com.example.fit_x_logger_android.main


import android.app.Application
import com.example.fit_x_logger_android.models.EmployeeJSONStore
import com.example.fit_x_logger_android.models.EmployeeStore
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info


/*
    Author Robert Solomon
    Student No.: 20079462
  */

class MainApp : Application(), AnkoLogger {

    lateinit var empDatas: EmployeeStore

    override fun onCreate() {
        super.onCreate()
        empDatas = EmployeeJSONStore(applicationContext)
        info("FIT-X-LOGGER App started")
    }
}