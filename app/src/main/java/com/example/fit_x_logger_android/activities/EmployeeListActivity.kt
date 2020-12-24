package com.example.fit_x_logger_android.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fit_x_logger_android.R
import com.example.fit_x_logger_android.main.MainApp
import com.example.fit_x_logger_android.models.EmployeeModel
import kotlinx.android.synthetic.main.activity_mainlist.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult


// Declaring the implemented interface in this class
class EmployeeListActivity : AppCompatActivity(), EmployeeDataListener {

   private lateinit var app: MainApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainlist)

        app = application as MainApp
        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = EmployeeDataAdapter(app.empDatas.findAll(), this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_add -> startActivityForResult<MainActivity>(0)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onEmployeeDataClick(empData: EmployeeModel) {
        startActivityForResult(intentFor<MainActivity>().putExtra("Employee_data_edit", empData), AppCompatActivity.RESULT_OK)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        recyclerView.adapter?.notifyDataSetChanged()
        super.onActivityResult(requestCode, resultCode, data)
    }
}