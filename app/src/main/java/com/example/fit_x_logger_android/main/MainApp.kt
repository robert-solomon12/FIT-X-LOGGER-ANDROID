package com.example.fit_x_logger_android.main


import android.app.Application
import com.example.fit_x_logger_android.models.EmployeeMemStore
import com.example.fit_x_logger_android.models.EmployeeModel
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info

class MainApp : Application(), AnkoLogger {

    val empDatas = EmployeeMemStore()

    override fun onCreate() {
        super.onCreate()
        info("FIT-X-LOGGER App started")
    }
}