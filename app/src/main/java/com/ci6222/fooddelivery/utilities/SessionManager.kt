package com.ci6222.fooddelivery.utilities

import android.content.Context
import android.content.SharedPreferences


object SessionManager {

    private const val NAME = sharePreferenceUser
    private const val MODE = Context.MODE_PRIVATE
    private lateinit var preferences: SharedPreferences


    fun init(context: Context) {
        preferences = context.getSharedPreferences(NAME, MODE)
    }

    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
        val editor = edit()
        operation(editor)
        editor.apply()
    }

//    var login_status: Boolean
//
//        get() = preferences.getBoolean(sharePreferenceLoginStatus, false)
//
//        set(value) = preferences.edit {
//            it.putBoolean(sharePreferenceLoginStatus, value)
//        }

    var patient_name: String?

        get() = preferences.getString(sharePreferencePatientName, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientName, value)
        }

    var patient_email: String?

        get() = preferences.getString(sharePreferencePatientEmail, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientEmail, value)
        }



    var patient_phone: String?

        get() = preferences.getString(sharePreferencePatientPhone, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientPhone, value)
        }




    var patient_photo : String?

        get() = preferences.getString(sharePreferencePatientProfile, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientProfile, value)
        }


    var login_status : Boolean?

        get() = preferences.getBoolean(Login_Status, false)

        set(value) = preferences.edit {
            value?.let { it1 -> it.putBoolean(Login_Status, it1) }
        }



    var  patient_device_token : String?

        get() = preferences.getString(sharePreferencePatientToken, "")

        set(value) = preferences.edit {
            it.putString(sharePreferencePatientToken, value)
        }

    var  userID : String?

        get() = preferences.getString(sharePreferenceUserID, "")

        set(value) = preferences.edit {
            it.putString(sharePreferenceUserID, value)
        }




}