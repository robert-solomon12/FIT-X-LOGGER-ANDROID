package com.example.fit_x_logger_android.activities


import android.content.Intent
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
import readImage
import readImageFromPath
import showImagePicker

class MainActivity : AppCompatActivity(), AnkoLogger {

    var empData = EmployeeModel()
    lateinit var app: MainApp
    val IMAGE_REQUEST = 1
    var edit = false


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


        if (intent.hasExtra("Employee_data_edit")) {
            edit = true
            empData = intent.extras?.getParcelable<EmployeeModel>("Employee_data_edit")!!
            empName.setText(empData.name)

            empDateOfB.setText(empData.dateOfB)
            empEmail.setText(empData.email)
            empGender.setText(empData.gender)
            empSsNumber.setText(empData.ssNumber)
            empNationality.setText(empData.nationality)
            empJobTitle.setText(empData.jobTitle)
            empHeight.setText(empData.height)
            empWeight.setText(empData.weight)
            empBMI.setText(empData.bmi)
            empDataImage.setImageBitmap(readImageFromPath(this, empData.profilePic))
            if (empData.profilePic != null) {
                chooseImage.setText(R.string.change_image)
            }
            btnAdd.setText(R.string.save_Btn)
        }

        btnAdd.setOnClickListener() {
            empData.name = empName.text.toString()
            empData.dateOfB = empDateOfB.text.toString()
            empData.email = empEmail.text.toString()
            empData.gender = empGender.text.toString()
            empData.ssNumber = empSsNumber.text.toString()
            empData.nationality = empNationality.text.toString()
            empData.jobTitle = empJobTitle.text.toString()
            empData.height = empHeight.text.toString()
            empData.weight = empWeight.text.toString()
            empData.bmi = empBMI.text.toString()
            if (empData.name.isEmpty()) {
                toast(R.string.promptInvalid_Emp)
            } else {
                if (edit) {
                    app.empDatas.update(empData.copy())
                } else {
                    app.empDatas.create(empData.copy())
                }
            }
            info(R.string.promptValid_Emp)
            setResult(AppCompatActivity.RESULT_OK)
            finish()
        }
        //event handler for Image
        chooseImage.setOnClickListener {
            showImagePicker(this, IMAGE_REQUEST)
        }

        btnCalBMI.setOnClickListener{
            calculateBMI()

        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_employeedata, menu)
        if (edit && menu != null) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }


    //formula for calculating bmi
    private fun calculateBMI(){
        val w = Weight.text.toString().toFloat() //retrieving the weight value from the edit text and converting to float
        //info ("Weight: $w")
        val h = Height.text.toString().toFloat() / 100 //getting the height value and converting it to a meter
        //info ("Height: $h")
        val res = (w/h*h) //this is the basic formula to calculate for each Employee
        //info ("Resulting BMI: $res")
        result.text = "%.2f".format(res) //formatting the result to display only to decimal form

        //result is then finally displayed
    }



    //inflating the menu after cancel button is enabled
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            R.id.item_delete -> {
                app.empDatas.delete(empData)
                finish()

            }

            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_REQUEST -> {
                if (data != null) {
                    empData.profilePic = data.getData().toString()
                    empDataImage.setImageBitmap(readImage(this, resultCode, data))
                    chooseImage.setText(R.string.change_image)
                }
            }
        }
    }
}