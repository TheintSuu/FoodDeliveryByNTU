package com.ci6222.fooddelivery.fragments

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ci6222.fooddelivery.mvp.presenter.AbstractBasePresenter
import com.ci6222.fooddelivery.mvp.view.BaseView
import com.google.android.material.snackbar.Snackbar

abstract class BaseFragment : Fragment(), BaseView
{
    override fun showError(error: String) {
          // Snackbar.make(window.decorView, error, Snackbar.LENGTH_LONG).show()
    }
    inline fun <reified T : AbstractBasePresenter<W>, reified W: BaseView> getPresenter(): T {
        val presenter = ViewModelProviders.of(this).get(T::class.java)
        if (this is W) presenter.initPresenter(this)
        return presenter
    }
}