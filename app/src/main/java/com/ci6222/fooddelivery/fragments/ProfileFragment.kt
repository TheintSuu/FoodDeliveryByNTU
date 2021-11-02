package com.ci6222.fooddelivery.fragments

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.activities.LoginActivity
import com.ci6222.fooddelivery.activities.RestartuantDetailActivity
import com.ci6222.fooddelivery.datas.vos.UserVO
import com.ci6222.fooddelivery.mvp.presenter.ProfilePresenter
import com.ci6222.fooddelivery.mvp.presenter.ProfilePresenterImpl
import com.ci6222.fooddelivery.mvp.view.ProfileView
import com.ci6222.fooddelivery.utilities.SessionManager
import com.ci6222.fooddelivery.utilities.showImage
import kotlinx.android.synthetic.main.fragment_account.*
import kotlinx.android.synthetic.main.fragment_account.account_btngroup
import kotlinx.android.synthetic.main.fragment_account.etEmail
import kotlinx.android.synthetic.main.fragment_account.etPassword
import kotlinx.android.synthetic.main.fragment_account.etPhone
import kotlinx.android.synthetic.main.fragment_account.etUserName
import kotlinx.android.synthetic.main.fragment_account.img_edit
import kotlinx.android.synthetic.main.fragment_account.img_profile
import kotlinx.android.synthetic.main.fragment_account.tv_cancel
import kotlinx.android.synthetic.main.fragment_account.tv_save
import kotlinx.android.synthetic.main.fragment_profile.*
import java.io.IOException


class ProfileFragment :  BaseFragment() , ProfileView {

    private lateinit var mPresenter: ProfilePresenter

    private  var bitmap : Bitmap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpActionListener()
    }

    private fun setUpActionListener()
    {
        img_edit.setOnClickListener{
            mPresenter?.onTapEditProfileImage()
        }
        tv_save.setOnClickListener{
            bitmap?.let { mPresenter.updateUserProfile(it) }
        }
        tv_cancel.setOnClickListener{
            bitmap?.let {
                mPresenter.onTapCancelUserData()
            }
        }
        btnSingOut.setOnClickListener {
           mPresenter.onTapLogOut()
        }
    }
    private fun setUpPresenter() {
        activity?.let{
            mPresenter = getPresenter<ProfilePresenterImpl, ProfileView>()
            context?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ProfileFragment.PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            val filePath = data.data
            try {
                filePath?.let {
                    if (Build.VERSION.SDK_INT >= 29) {
                        val source: ImageDecoder.Source = ImageDecoder.createSource(context?.contentResolver!!, filePath)
                        bitmap = ImageDecoder.decodeBitmap(source)
                        account_btngroup.visibility = View.VISIBLE
                        showImage(img_profile.context,img_profile,null,filePath)
                    } else {
                        val bitmap = MediaStore.Images.Media.getBitmap(context?.contentResolver, filePath)
                        showImage(img_profile.context,img_profile,null,filePath)
                        account_btngroup.visibility = View.GONE
                    }
                }

            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }

    fun openGallery() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), ProfileFragment.PICK_IMAGE_REQUEST)
    }

    companion object {
        const val PICK_IMAGE_REQUEST = 1111
        @JvmStatic
        fun newInstance(param1: String, param2: String) = ProfileFragment().apply {}
    }

    override fun onTapSaveUserData() {
        account_btngroup.visibility = View.GONE
    }

    override fun onTapCancelUserData() {
        account_btngroup.visibility = View.GONE
    }

    override fun onTapEditProfileImage() {
        openGallery()
    }

    override fun displayUserData(userVO: UserVO) {
        etUserName.text = Editable.Factory.getInstance().newEditable(userVO.name)
        etEmail.text = Editable.Factory.getInstance().newEditable(userVO.email)
        etPassword.text = Editable.Factory.getInstance().newEditable(userVO.password)
        etPhone.text = Editable.Factory.getInstance().newEditable(userVO.phone)
        showImage(img_profile.context,img_profile,userVO.image,null)
    }

    override fun displayLoginScreen() {
        SessionManager.login_status = false
        startActivity( activity?.applicationContext?.let{ LoginActivity.newIntent(it)})
    }


}