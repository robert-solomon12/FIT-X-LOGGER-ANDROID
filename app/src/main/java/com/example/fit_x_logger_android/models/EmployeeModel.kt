package com.example.fit_x_logger_android.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class EmployeeModel (
    var id: Int = 0,
    var fName: String = "",
    var sName: String = "",
    var dateOfB: String = "",
    var email: String = "",
    var ssNumber: String = "",
    var nationality: String = "",
    var jobTitle: String = "") : Parcelable