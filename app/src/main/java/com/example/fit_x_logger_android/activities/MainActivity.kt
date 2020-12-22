package com.example.fit_x_logger_android.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.fit_x_logger_android.R
import com.example.fit_x_logger_android.main.MainApp
import com.example.fit_x_logger_android.models.EmployeeModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity(), AnkoLogger {

    var empData = EmployeeModel()
    lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*
         explicitly enabling the toolbar here upon calling the onCreate method for presenting the cancelling button here
         */
        toolbarAdd.title = title
        setSupportActionBar(toolbarAdd)

        info("FIT-X-LOGGER App Activity started..")

        app = application as MainApp

        if (intent.hasExtra("Employee_edit")) {
            empData = intent.extras?.getParcelable<EmployeeModel>("Employee_edit")!!
            empfName.setText(empData.fName)
            empsName.setText(empData.sName)
            empDateOfB.setText(empData.dateOfB)
            empEmail.setText(empData.email)
            empSsNumber.setText(empData.ssNumber)
            empNationality.setText(empData.nationality)
            empJobTitle.setText(empData.jobTitle)
        }

        btnAdd.setOnClickListener() {
            empData.fName = empfName.text.toString()
            empData.sName = empsName.text.toString()
            empData.dateOfB = empDateOfB.text.toString()
            empData.email = empEmail.text.toString()
            empData.ssNumber = empSsNumber.toString()
            empData.nationality = empNationality.toString()
            empData.jobTitle = empJobTitle.toString()
            if (empData.fName.isNotEmpty() && empData.sName.isNotEmpty() && empData.dateOfB.isNotEmpty()
                && empData.ssNumber.isNotEmpty() && empData.email.isNotEmpty() && empData.nationality.isNotEmpty() && empData.jobTitle.isNotEmpty()) {
                app.empDatas.create(empData.copy())
                info("Add Button Pressed: $empfName")
                setResult(AppCompatActivity.RESULT_OK)
                finish()
            } else {
                toast("Please Enter valid Employee Data")
            }
        }
    }


    //inflating the menu after cancel button is enabled
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_employeedata, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_cancel -> {
                finish()
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
