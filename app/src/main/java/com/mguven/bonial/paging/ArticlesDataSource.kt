package com.mguven.bonial.paging

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import android.support.v7.util.DiffUtil
import com.mguven.bonial.model.Article
import com.mguven.bonial.network.NewsApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import timber.log.Timber

class ArticlesDataSource(private val newsApi: NewsApi,
                         private val compositeDisposable: CompositeDisposable)
  : PageKeyedDataSource<Int, Article>() {

  val networkState: MutableLiveData<NetworkState> = MutableLiveData()

  override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Article>) {
    networkState.postValue(NetworkState.LOADING)
    createObservable(1, 2, params.requestedLoadSize, callback, null)
  }

  override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    networkState.postValue(NetworkState.LOADING)
    createObservable(params.key, params.key + 1, params.requestedLoadSize, null, callback)
  }

  override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Article>) {
    createObservable(params.key, params.key - 1, params.requestedLoadSize, null, callback)
  }

  private fun createObservable(page: Int,
                               adjacentPage: Int,
                               requestedLoadSize: Int,
                               initialCallback: LoadInitialCallback<Int, Article>?,
                               callback: LoadCallback<Int, Article>?) {
    compositeDisposable.add(
        newsApi.getNews(page, requestedLoadSize)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { response ->
                  networkState.postValue(NetworkState.LOADED)
                  initialCallback?.onResult(response.articles!!, null, adjacentPage)
                  callback?.onResult(response.articles!!, adjacentPage)
                },
                {
                  Timber.e(it)
                  networkState.postValue(NetworkState.FAILED)
                }
            ))
  }

}
