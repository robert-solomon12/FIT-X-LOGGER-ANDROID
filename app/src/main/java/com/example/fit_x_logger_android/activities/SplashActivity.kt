package com.example.fit_x_logger_android.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fit_x_logger_android.R

/*
    Author Robert Solomon
    Student No.: 20079462
  */

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashview)

        //hide method to hide the top actionbar during the splashview
        supportActionBar?.hide()

        //Handler for delaying the time period for the splashview before using the intent function to processing to the next view
        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, EmployeeListActivity::class.java)
            startActivity(intent)
        }, 3000)
    }


}