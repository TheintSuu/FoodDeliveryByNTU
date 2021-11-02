package com.ci6222.fooddelivery.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO
import com.ci6222.fooddelivery.mvp.presenter.DetailPresenter
import com.ci6222.fooddelivery.mvp.presenter.DetailPresenterImpl
import com.ci6222.fooddelivery.mvp.view.DetailView
import com.ci6222.fooddelivery.utilities.showImageWithoutCrop
import com.example.fooddeliveryapp.adapters.DetailAdapter
import com.example.fooddeliveryapp.adapters.PopularChoiceDetailAdapter
import kotlinx.android.synthetic.main.activity_restartuant_detail.*
import kotlinx.android.synthetic.main.content_scrolling.*

class RestartuantDetailActivity : BaseActivity() , DetailView {

    private lateinit var mPresenter: DetailPresenter
    private lateinit var mDetailAdapter: DetailAdapter
    private lateinit var mPopularChoiceDetailAdapter: PopularChoiceDetailAdapter
    private lateinit var mRestaurantVO: RestaurantVO
     var documentID  : String = ""

    companion object {
        const val PARM_DOCUMENTID = "Document ID"
        fun newIntent(context: Context,
                      documentId: String
        ) : Intent {
            val intent = Intent(context, RestartuantDetailActivity::class.java)

            intent.putExtra(PARM_DOCUMENTID, documentId)
            return intent
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restartuant_detail)
        setUpPresenter()
        setUpRecyclerView()
        setUpActionListener()
        documentID = intent.getStringExtra(PARM_DOCUMENTID).toString()

        mPresenter.onUiReady(this,this)
        mPresenter.onRestaurantReceived(this,this,intent.getStringExtra(PARM_DOCUMENTID).toString())
    }
    private fun setUpActionListener()
    {

        btn_viewcart.setOnClickListener {
            mRestaurantVO?.let {
                startActivity(CheckOutActivity.newIntent(this,it?.name,it?.description,it?.image_Url,it?.rating, documentID))
            }

        }
        toolbar.setOnClickListener {
            this.finish()
        }
    }
    private fun setUpRecyclerView() {

        rc_detail_popular_choice.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        rc_detail_fooditem.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        mDetailAdapter = DetailAdapter (mPresenter)
        rc_detail_fooditem.adapter = mDetailAdapter

        mPopularChoiceDetailAdapter = PopularChoiceDetailAdapter (mPresenter)
        rc_detail_popular_choice.adapter = mPopularChoiceDetailAdapter

    }


    private fun setUpPresenter() {
        mPresenter = getPresenter<DetailPresenterImpl, DetailView>()


    }


    override fun showRestaurantData(restaurantVO: RestaurantVO) {
        mRestaurantVO =restaurantVO
        detail_description.text =restaurantVO?.description
        tv_detail_rating.text =restaurantVO?.rating
        tv_detail_title.text =restaurantVO?.name
        restaurantVO?.image_Url?.let{
            showImageWithoutCrop(detail_image,it)
        }
    }

    override fun showPopularChoicesFoodItem(popularFoodList: List<FoodItemVO>) {
        mPopularChoiceDetailAdapter.setData(popularFoodList as MutableList<FoodItemVO>)
    }

    override fun showFoodItemList(foodList: List<FoodItemVO>) {
        mDetailAdapter.setData(foodList as MutableList<FoodItemVO>)
    }

    override fun showViewCartCount(cartCount: Long) {
        if(cartCount>0)
        {
            btn_viewcart.visibility= View.VISIBLE
            btn_viewcart.text="View Cart ${cartCount} items"
        }else{
            btn_viewcart.visibility= View.GONE
        }
    }


}