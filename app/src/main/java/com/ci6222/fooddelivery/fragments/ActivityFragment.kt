package com.ci6222.fooddelivery.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ci6222.fooddelivery.R
import com.ci6222.fooddelivery.activities.RestartuantDetailActivity
import com.ci6222.fooddelivery.adapters.ActivityAdapter
import com.ci6222.fooddelivery.datas.vos.ActivityVO
import com.ci6222.fooddelivery.mvp.presenter.ActivityPresenter
import com.ci6222.fooddelivery.mvp.presenter.ActivityPresenterImpl
import com.ci6222.fooddelivery.mvp.presenter.MainPresenterImpl
import com.ci6222.fooddelivery.mvp.view.ActivityView
import com.ci6222.fooddelivery.mvp.view.MainView
import com.ci6222.fooddelivery.utilities.SessionManager
import com.ci6222.fooddelivery.utilities.getCurrentHourMinAMPM
import com.ci6222.fooddelivery.utilities.showImageWithoutCrop
import com.ci6222.fooddelivery.viewholders.EmptyViewPod
import com.example.fooddeliveryapp.adapters.CategoryAdapter
import kotlinx.android.synthetic.main.fragment_activity.*
import kotlinx.android.synthetic.main.fragment_restartuant.*


class ActivityFragment : BaseFragment(), ActivityView {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var mDownloadAdapter: ActivityAdapter

    lateinit var mPresenter : ActivityPresenter

    private lateinit var mviewPodEmpty: EmptyViewPod


    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ActivityFragment().apply {
                arguments = Bundle().apply {}
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // Inflate the layout for this fragmentDow
        val v = inflater.inflate(R.layout.fragment_activity, container, false)


        setUpPresenter()

        setUpAdapter()


        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setUpViewPod()

        setUpRecyclerView()

    }


    private fun setUpPresenter(){
        activity?.let{
            mPresenter = getPresenter<ActivityPresenterImpl, ActivityView>()
            context?.let { it1 -> mPresenter.onUiReady(it1,this) }
        }
    }

    private fun setUpRecyclerView(){

        val linearLayoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mainRecylerDownload.layoutManager = linearLayoutManager
        mainRecylerDownload.adapter =  mDownloadAdapter

        mainRecylerDownload.setEmptyView(mviewPodEmpty)

    }

    private fun setUpViewPod() {
        mviewPodEmpty = vpEmpty as EmptyViewPod
        // mviewPodEmpty.setEmptyData(EM_NO_NEWS_AVAILABLE, EMPTY_IMAGE_URL)
        mviewPodEmpty.setDelegate(mPresenter)
    }


    private  fun setUpAdapter(){
        mDownloadAdapter = ActivityAdapter (mPresenter)
        // mainRecylerDownload.adapter = mDownloadAdapter
    }

    override fun showCheckOutData(popularFoodList: List<ActivityVO>) {
        mDownloadAdapter.setData(popularFoodList.toMutableList())
    }

    override fun navigateToDetail(documentID: String) {
        startActivity( activity?.applicationContext?.let{ RestartuantDetailActivity.newIntent(it, documentID)})
    }





}