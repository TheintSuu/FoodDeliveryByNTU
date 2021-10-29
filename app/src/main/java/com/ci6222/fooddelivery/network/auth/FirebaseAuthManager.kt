package com.ci6222.fooddelivery.network.auth

import android.graphics.Bitmap
import android.net.Uri
import com.ci6222.fooddelivery.data.vos.UserVO
import com.ci6222.fooddelivery.network.CloudFirestoreFirebaseApiImpl.storageReference
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import java.io.ByteArrayOutputStream
import java.util.*


object FirebaseAuthManager : AuthManager {

    private val mFirebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()

    override fun login(email: String, password: String, onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mFirebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful && it.isComplete){
                onSuccess()
            } else {
                onFailure(it.exception?.message ?: "Please Check Internet Connection")
            }
        }
    }

    override fun register(
            userName: String,
        email: String,
        password: String,
        phone : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mFirebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful && it.isComplete) {
                mFirebaseAuth.currentUser?.updateProfile(

                    UserProfileChangeRequest.Builder().setDisplayName(userName).build()

                )

                onSuccess()
            } else {
                onFailure(it.exception?.message ?: "Please check internet connection")
            }
        }
    }

    override fun getUserName(): String {
        return mFirebaseAuth.currentUser?.displayName ?: ""
    }

    override fun getAllUserData(): UserVO {
        return UserVO(
                mFirebaseAuth.currentUser?.displayName ?:"",
                mFirebaseAuth.currentUser?.email ?:"",
                mFirebaseAuth.currentUser?.photoUrl.toString() ?:""
        )
    }

    override fun uploadPhototUrl(photoUrl: Bitmap, onSuccess: (String) -> Unit, onFailure: (String) -> Unit) {
        val baos = ByteArrayOutputStream()
        photoUrl.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            //
           // onFailure(it)
        }.addOnSuccessListener { taskSnapshot ->
            //
        }

        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            val photoUrl = task.result?.toString()
            photoUrl?.let{
                onSuccess(it)
            }
        }
    }


    override  fun updateProfile(
        photoUrl : String,
        onSuccess: () -> Unit,
        onFailure: (String) -> Unit
    )
    {
        mFirebaseAuth.currentUser?.updateProfile(UserProfileChangeRequest.Builder().
        setPhotoUri( Uri.parse(photoUrl)).build())?.addOnCompleteListener {
                task -> if(task.isSuccessful)
        {   onSuccess() } else{
            onFailure("Fail Profile Update")}
        }
    }



}