package com.ci6222.fooddelivery.network.auth

import android.content.ContentValues
import android.net.Uri
import android.util.Log
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.utilities.SessionManager
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest



object FirebaseAuthManager : AuthManager {

    private val mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override  fun updateProfile(
        photoUrl : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
    {
        mFirebaseAuth.currentUser?.updateProfile(UserProfileChangeRequest.Builder().
        setPhotoUri( Uri.parse(photoUrl)).build())?.addOnCompleteListener {
                task -> if(task.isSuccessful)
        {   onSuccess() } else{  onFailure("Fail Profile Update")}
        }
    }

    override fun login(
        email: String,
        password: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful && it.isComplete){
                onSuccess()
            } else {
                onFailure(it.exception?.message ?: "Please Check Internet Connection")
            }
        }
    }

    override fun register(
        username: String,
        email: String,
        password: String,
        phone: String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                mFirebaseAuth.currentUser?.updateProfile(
                    UserProfileChangeRequest.Builder().setDisplayName(username).build()
                )
                onSuccess()
            } else {
                onFailure(it.exception?.message ?: "Please check internet connection")
            }
        }
    }

    override fun userData(onSuccess: (userVO: UserVO) -> Unit, onFailure: (String) -> Unit) {
        var user = mFirebaseAuth.currentUser
        val userVO : UserVO = UserVO()
        if (user != null) {
            userVO?.name = user.displayName.toString()
            userVO?.email = user.email.toString()
            userVO?.image = user.photoUrl.toString()
            Log.d(ContentValues.TAG, "User profile updated."+ user.photoUrl.toString())
            userVO?.phone =  user.phoneNumber.toString()
            onSuccess(userVO)
        }else{
            onFailure("Empty UserData")
        }
    }

    override fun userDataByEmail(
        email: String,
        onSuccess: (userVO: UserVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        var user = mFirebaseAuth.currentUser
        val userVO : UserVO = UserVO()
        if (user != null) {
            userVO?.name = user.displayName.toString()
            userVO?.email = user.email.toString()
            userVO.device_token = SessionManager.patient_device_token
            userVO?.image = user.photoUrl.toString()
            Log.d(ContentValues.TAG, "User profile updated."+ user.photoUrl.toString())
            userVO?.phone =  user.phoneNumber.toString()
            onSuccess(userVO)
        }else{
            onFailure("Empty UserData")
        }
    }
}