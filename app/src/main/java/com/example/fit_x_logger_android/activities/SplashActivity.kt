package com.example.fit_x_logger_android.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.fit_x_logger_android.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashview)

        supportActionBar?.hide()


        Handler().postDelayed({
            val intent = Intent(this@SplashActivity, EmployeeListActivity::class.java)
            startActivity(intent)
        }, 3000)
    }


}