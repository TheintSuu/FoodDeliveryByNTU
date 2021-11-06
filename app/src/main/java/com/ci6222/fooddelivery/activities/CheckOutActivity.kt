package com.ci6222.fooddelivery.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO
import com.ci6222.fooddelivery.mvp.presenter.CheckOutPresenter
import com.ci6222.fooddelivery.mvp.presenter.CheckOutPresenterImpl
import com.ci6222.fooddelivery.mvp.view.CheckOutView
import com.ci6222.fooddelivery.utilities.showImageWithoutCrop
import com.example.fooddeliveryapp.adapters.CheckOutAdapter
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.checkout_success_layout.view.*
import kotlinx.android.synthetic.main.view_holder_checkout.*
import kotlinx.android.synthetic.main.activity_check_out_actvity.*


class CheckOutActivity : BaseActivity(), CheckOutView {

    private lateinit var mPresenter: CheckOutPresenter
    private lateinit var mAdapter: CheckOutAdapter
    private lateinit var mOrderList: List<FoodItemVO>
    private val restInfo = RestaurantVO()

    companion object {
        const val PARM_RESTAURANT_IMAGE = "PARM_RESTAURANT_IMAGE"
        const val PARM_RESTAURANT_NAME = "PARM_RESTAURANT_NAME"
        const val PARM_RESTAURANT_DESRIPTION = "PARM_RESTAURANT_DESRIPTION"
        const val PARM_RESTAURANT_RATING = "PARM_RESTAURANT_RATING"
        const val PARM_RESTAURANT_DOCUMENTID = "PARM_RESTAURANT_ID"
        fun newIntent(context: Context,
                      restaurant_name: String?, restaurant_description: String?,
                      restaurant_image: String?, restaurant_rating: String?, documentID : String ?
        ): Intent {
            val intent = Intent(context, CheckOutActivity::class.java)
            intent.putExtra(PARM_RESTAURANT_IMAGE, restaurant_image)
            intent.putExtra(PARM_RESTAURANT_NAME, restaurant_name)
            intent.putExtra(PARM_RESTAURANT_DESRIPTION, restaurant_description)
            intent.putExtra(PARM_RESTAURANT_RATING, restaurant_rating)
            intent.putExtra(PARM_RESTAURANT_DOCUMENTID, documentID)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out_actvity)
        setUpPresenter()
        initView()
        setUpRecyclerView()
        setUpActionListener()
    }

    private fun initView() {
        tv_restaurant_name.text = intent.getStringExtra(PARM_RESTAURANT_NAME).toString()
        restInfo.name = intent.getStringExtra(PARM_RESTAURANT_NAME).toString()
        tv_restaurant_description.text = intent.getStringExtra(PARM_RESTAURANT_DESRIPTION).toString()
        restInfo.image_Url = intent.getStringExtra(PARM_RESTAURANT_IMAGE)
        restInfo.id = intent.getStringExtra(PARM_RESTAURANT_DOCUMENTID)
        tv_restaurant_rating.text = intent.getStringExtra(PARM_RESTAURANT_RATING).toString()
        intent.getStringExtra(PARM_RESTAURANT_IMAGE)?.let {
            showImageWithoutCrop(img_restaurant, it)
        }
    }

    private fun setUpActionListener() {
        btn_checkout.setOnClickListener {
            if(mOrderList.isNotEmpty()) {
                showBottomSheetDialog()
                mPresenter.onTapTrack(this, this)
            }else
            {
                Toast.makeText(this,"Empty Cart Item", Toast.LENGTH_LONG).show()
            }
        }
        ll_backpress.setOnClickListener {
            onBackPressed()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }


    private fun showBottomSheetDialog() {
        val view = layoutInflater.inflate(R.layout.checkout_success_layout, null)
        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)

        view.btn_order_track.setOnClickListener {

            Toast.makeText(this, "Order Track Clicked", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
            mPresenter.onTapCheckout(this,orderList = mOrderList, restInfo)
            exitActivity()

        }
        view.btn_order_something.setOnClickListener {

            Toast.makeText(this, "Order Cancel Clicked", Toast.LENGTH_SHORT).show()
            dialog?.dismiss()
        }
        dialog.show()
    }

    private fun exitActivity() {
        this.finish()
    }

    private fun setUpRecyclerView() {

        rc_orderList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mAdapter = CheckOutAdapter(mPresenter)
        rc_orderList.adapter = mAdapter

    }


    private fun setUpPresenter() {
        mPresenter = getPresenter<CheckOutPresenterImpl, CheckOutView>()
        mPresenter.onUiReady(this,this)

    }

    override fun showOrderList(orderList: List<FoodItemVO>) {
        mOrderList = orderList
        mAdapter.setData(orderList as MutableList<FoodItemVO>)
    }

    override fun showCalculationCharge(totalPrice: Double) {
        val price =  String.format("%.2f", totalPrice)
        tv_total_Amount.text = "${price} $"

    }


}