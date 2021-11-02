package com.ci6222.fooddelivery.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.activities.RestartuantDetailActivity
import com.ci6222.fooddelivery.datas.vos.CategoryVO
import com.ci6222.fooddelivery.datas.vos.FoodItemVO
import com.ci6222.fooddelivery.datas.vos.RestaurantVO
import com.ci6222.fooddelivery.mvp.presenter.MainPresenter
import com.ci6222.fooddelivery.mvp.presenter.MainPresenterImpl
import com.ci6222.fooddelivery.mvp.view.MainView
import com.ci6222.fooddelivery.utilities.SessionManager
import com.ci6222.fooddelivery.utilities.getCurrentHourMinAMPM
import com.example.fooddeliveryapp.adapters.CategoryAdapter
import com.example.fooddeliveryapp.adapters.PopularChoiceAdapter
import com.example.fooddeliveryapp.adapters.RestaurantAdapter
import kotlinx.android.synthetic.main.fragment_restartuant.*


class RestaurantFragment : BaseFragment(), MainView {

    private lateinit var mPresenter: MainPresenter

    private lateinit var mCategoryAdapter: CategoryAdapter
    private lateinit var mRestaurantAdapter: RestaurantAdapter
    private lateinit var mPopularChoiceAdapter: PopularChoiceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_restartuant , container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPresenter()
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {

        rc_category.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rc_popular_choice.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        rc_restaurants.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        mCategoryAdapter = CategoryAdapter (mPresenter)
        rc_category.adapter = mCategoryAdapter

        mPopularChoiceAdapter = PopularChoiceAdapter (mPresenter)
        rc_popular_choice.adapter = mPopularChoiceAdapter

    }


    private fun setUpPresenter() {
        activity?.let{
            mPresenter = getPresenter<MainPresenterImpl, MainView>()
            context?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun changeHomeScreenViewType(viewType: Int) {
        if(viewType == 0)
        {
            viewTypeOne()
        }else
        {
            viewTypeTwo()
        }
    }

    private fun viewTypeOne()
    {
        location_layout.visibility =View.VISIBLE
        rc_category.visibility = View.VISIBLE
        ly_restaurant.visibility= View.GONE
        ly_popular.visibility =View.GONE
        rc_popular_choice.visibility =View.GONE
        mRestaurantAdapter = RestaurantAdapter (mPresenter,0)
        rc_restaurants.adapter = mRestaurantAdapter

    }
    private fun viewTypeTwo()
    {
        location_layout.visibility =View.GONE
        rc_category.visibility = View.GONE
        ly_restaurant.visibility= View.VISIBLE
        ly_popular.visibility =View.VISIBLE
        rc_popular_choice.visibility =View.VISIBLE
        mRestaurantAdapter = RestaurantAdapter (mPresenter,1)
        rc_restaurants.adapter = mRestaurantAdapter

    }

    override fun navigateToDetailScreen(documentId: String) {
        startActivity( activity?.applicationContext?.let{RestartuantDetailActivity.newIntent(it, documentId)})
    }

    override fun showErrorMessage(message: String) {
        //   Snackbar.make(window.decorView, message, Snackbar.LENGTH_LONG)
    }

    override fun showCategories(categoryList: List<CategoryVO>) {
        setUserMenu()
        mCategoryAdapter.setData(categoryList as MutableList<CategoryVO>)
    }

    override fun showRestaurants(restaurantList: List<RestaurantVO>) {
        mRestaurantAdapter.setData(restaurantList as MutableList<RestaurantVO>)
    }

    override fun showPopularChoicesFoodItems(popularChoiceList: List<FoodItemVO>) {
        mPopularChoiceAdapter.setData(popularChoiceList as MutableList<FoodItemVO>)
    }

    private fun setUserMenu(){
        tvUserWelcome.text = "Hi! "+ SessionManager.patient_name+" ,"
        val currentTime =   getCurrentHourMinAMPM()
        if(currentTime.contains("AM")){

            activity?.applicationContext?.let {
                ivMenu.setImageDrawable(AppCompatResources.getDrawable(it, R.drawable.breakfast))
            }
        }else if(currentTime.contains("PM") ){

            activity?.applicationContext?.let {
                ivMenu.setImageDrawable(AppCompatResources.getDrawable(it, R.drawable.dinner))
            }
        }else {
            activity?.applicationContext?.let {
                ivMenu.setImageDrawable(AppCompatResources.getDrawable(it, R.drawable.lunch))
            }
        }
    }

}