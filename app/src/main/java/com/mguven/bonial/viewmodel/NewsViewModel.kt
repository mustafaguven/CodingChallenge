package com.mguven.bonial.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.mguven.bonial.model.Article
import com.mguven.bonial.network.NewsApi
import com.mguven.bonial.paging.ArticlesDataSourceFactory
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import androidx.lifecycle.Transformations
import com.mguven.bonial.paging.ArticlesDataSource
import com.mguven.bonial.paging.NetworkState


class NewsViewModel @Inject
constructor(newsApi: NewsApi) : BaseViewModel() {

  companion object {
    const val PAGE_SIZE = 21
  }

  val networkState: LiveData<NetworkState>
  val newsList: Observable<PagedList<Article>>
  val articlesDataSourceFactory: ArticlesDataSourceFactory

  init {
    articlesDataSourceFactory = ArticlesDataSourceFactory(compositeDisposable, newsApi)

    networkState = Transformations.switchMap(articlesDataSourceFactory.articlesDataSourceLiveData) { dataSource ->
      dataSource.networkState
    }

    val config = PagedList.Config.Builder()
        .setPageSize(PAGE_SIZE)
        .setInitialLoadSizeHint(PAGE_SIZE)
        .setEnablePlaceholders(false)
        .build()

    newsList = RxPagedListBuilder(articlesDataSourceFactory, config)
        .setFetchScheduler(Schedulers.io())
        .buildObservable()

  }

  fun refresh() {
    articlesDataSourceFactory.articlesDataSourceLiveData.value!!.invalidate()
  }

  fun getRefreshState(): LiveData<NetworkState> = Transformations.switchMap<ArticlesDataSource, NetworkState>(
      articlesDataSourceFactory.articlesDataSourceLiveData) { it.networkState }

}
