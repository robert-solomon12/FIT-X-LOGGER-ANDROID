package com.example.fit_x_logger_android.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/*
    Author Robert Solomon
    Student No.: 20079462
  */

@Parcelize
data class EmployeeModel (
    var id: Long = 0,
    var name: String = "",
    var dateOfB: String = "",
    var email: String = "",
    var gender: String = "",
    var ssNumber: String = "",
    var nationality: String = "",
    var jobTitle: String = "",
    var height: String = "",
    var weight: String = "",
    var bmi: String = "",
    var result: String = "",
    var profilePic: String = "") : Parcelable