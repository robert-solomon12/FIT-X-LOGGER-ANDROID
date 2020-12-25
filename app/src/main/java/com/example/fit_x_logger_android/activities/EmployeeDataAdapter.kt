package com.example.fit_x_logger_android.activities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.fit_x_logger_android.R
import com.example.fit_x_logger_android.models.EmployeeModel
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.card_employeedata.view.*
import readImageFromPath

interface EmployeeDataListener {
    fun onEmployeeDataClick(empData: EmployeeModel)
}

class EmployeeDataAdapter constructor(private var empDatas: List<EmployeeModel>,
                                      private val listener: EmployeeDataListener) : RecyclerView.Adapter<EmployeeDataAdapter.MainHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
            return MainHolder(LayoutInflater.from(parent.context).inflate(R.layout.card_employeedata, parent, false))
        }

        override fun onBindViewHolder(holder: MainHolder, position: Int) {
            val empData = empDatas[holder.adapterPosition]
            holder.bind(empData, listener)
        }

        override fun getItemCount(): Int = empDatas.size

        class MainHolder constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {

            fun bind(empData: EmployeeModel, listener: EmployeeDataListener) {
                itemView.nameTv.text = empData.name
                itemView.dateofBTv.text = empData.dateOfB
                itemView.emailTv.text = empData.email
                itemView.genderTv.text = empData.gender
                itemView.ssNumberTv.text = empData.ssNumber
                itemView.nationalityTv.text = empData.nationality
                itemView.jobTitleTv.text = empData.jobTitle
                itemView.imageIcon.setImageBitmap(readImageFromPath(itemView.context, empData.profilePic))

                itemView.setOnClickListener { listener.onEmployeeDataClick(empData) }
            }
        }
    }