package com.mguven.bonial.ui

import android.annotation.SuppressLint
import androidx.lifecycle.Observer
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.mguven.bonial.BonialApplication
import com.mguven.bonial.R
import com.mguven.bonial.di.module.NewsActivityModule
import com.mguven.bonial.exception.UnintendedNetworkStateException
import com.mguven.bonial.extension.loadLayoutManagerInBonialStyle
import com.mguven.bonial.paging.NetworkState
import com.mguven.bonial.rx.SchedulerProvider
import com.mguven.bonial.ui.adapter.NewsAdapter
import com.mguven.bonial.viewmodel.NewsViewModel
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class NewsActivity : AbstractBaseActivity() {

  @Inject
  lateinit var newsAdapter: NewsAdapter

  @Inject
  lateinit var schedulerProvider: SchedulerProvider

  private lateinit var newsViewModel: NewsViewModel

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    inject()
    newsViewModel = getViewModel(NewsViewModel::class.java)
    initAdapter()
    initSwipeToRefresh()
  }

  @SuppressLint("CheckResult")
  private fun initAdapter() {
    rvNews.loadLayoutManagerInBonialStyle()
    rvNews.adapter = newsAdapter

    newsViewModel.networkState.observe(this, Observer { networkState ->
      newsAdapter.updateProgressState(networkState)
    })

    newsViewModel.newsList
        .observeOn(schedulerProvider.ui())
        .subscribe({ articleList ->
          newsAdapter.submitList(articleList)
        }, {
          Timber.e(it)
        })
  }


  private fun initSwipeToRefresh() {
    newsViewModel.getRefreshState().observe(this, Observer { networkState ->
      updateScreenByNetworkState(networkState)
    })
    swipeToRefresh.setOnRefreshListener { newsViewModel.refresh() }
  }

  private fun updateScreenByNetworkState(networkState: NetworkState?) {
    when (networkState) {
      NetworkState.LOADING -> {
        //todo: something about loading
      }
      NetworkState.FAILED -> {
        //todo: something about failed
        hideProgressAndSwipeToRefresh()
        Toast.makeText(this, "todo: will be retrieved from offline", Toast.LENGTH_SHORT).show()
      }
      NetworkState.LOADED -> {
        hideProgressAndSwipeToRefresh()
        //Toast.makeText(this, "todo: do something related to be loaded state", Toast.LENGTH_SHORT).show()
      }
      else -> {
        hideProgressAndSwipeToRefresh()
        Timber.e(UnintendedNetworkStateException())
        Toast.makeText(this, "todo: unintended state", Toast.LENGTH_SHORT).show()
      }
    }
  }

  private fun hideProgressAndSwipeToRefresh() {
    swipeToRefresh.isRefreshing = false
    progressBar.visibility = View.GONE
  }

  private fun inject() {
    (application as BonialApplication)
        .applicationComponent
        .plus(NewsActivityModule(this))
        .inject(this)
  }

}
