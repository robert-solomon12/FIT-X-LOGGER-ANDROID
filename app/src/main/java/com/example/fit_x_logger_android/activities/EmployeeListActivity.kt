package com.example.fit_x_logger_android.activities

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fit_x_logger_android.R
import com.example.fit_x_logger_android.main.MainApp
import com.example.fit_x_logger_android.models.EmployeeModel
import kotlinx.android.synthetic.main.activity_mainlist.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.startActivityForResult
import java.util.*
import kotlin.collections.ArrayList


// Declaring the implemented interface in this class
class EmployeeListActivity : AppCompatActivity(), EmployeeDataListener {

    lateinit var app: MainApp

    val arrayList = ArrayList<EmployeeModel>()
    val displayList = ArrayList<EmployeeModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mainlist)



        app = application as MainApp

        toolbar.title = title
        setSupportActionBar(toolbar)

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        //recyclerView.adapter = EmployeeDataAdapter(app.empDatas.findAll(), this)
        loadEmployeeData()


    }

    private fun loadEmployeeData() {
        showEmployeeData(app.empDatas.findAll())
    }

    fun showEmployeeData(empDatas: List<EmployeeModel>) {
        recyclerView.adapter = EmployeeDataAdapter(empDatas, this)
        recyclerView.adapter?.notifyDataSetChanged()
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
        startActivityForResult(intentFor<MainActivity>().putExtra("Employee_data_edit", empData), 0)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        loadEmployeeData()
        super.onActivityResult(requestCode, resultCode, data)
    }
}