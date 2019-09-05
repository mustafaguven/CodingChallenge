package com.mguven.bonial.ui.adapter

import androidx.paging.PagedListAdapter
import androidx.collection.SparseArrayCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import com.mguven.bonial.common.ViewType
import com.mguven.bonial.common.ViewTypeDelegateAdapter
import com.mguven.bonial.model.Article
import com.mguven.bonial.paging.NetworkState


class NewsAdapter : PagedListAdapter<Article, androidx.recyclerview.widget.RecyclerView.ViewHolder>(articleDiff) {

  private var networkState: NetworkState? = NetworkState.LOADING

  private val delegateAdapters = androidx.collection.SparseArrayCompat<ViewTypeDelegateAdapter>()

  companion object {
    const val CLUSTER_SIZE = 7

    val articleDiff = object : DiffUtil.ItemCallback<Article>() {
      override fun areItemsTheSame(old: Article, new: Article): Boolean {
        return old.title == new.title
      }

      override fun areContentsTheSame(old: Article, new: Article): Boolean {
        return old == new
      }
    }
  }

  init {
    delegateAdapters.put(NewsAdapterConstant.LOADING, LoadingDelegateAdapter())
    delegateAdapters.put(NewsAdapterConstant.ONE_ROW_ARTICLE, OneRowArticleDelegateAdapter())
    delegateAdapters.put(NewsAdapterConstant.NORMAL_ARTICLE, ArticleDelegateAdapter())
  }

  override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
    return delegateAdapters.get(viewType)!!.onCreateViewHolder(viewGroup)
  }

  private fun hasExtraRow(): Boolean = networkState == NetworkState.LOADED

  override fun onBindViewHolder(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
    delegateAdapters.get(getItemViewType(position))!!.onBindViewHolder(viewHolder, getItem(position) as ViewType)
  }

  override fun getItemCount(): Int {
    return super.getItemCount() + if (hasExtraRow()) 1 else 0
  }

  override fun getItemViewType(position: Int): Int {
    return if (position == itemCount - 1) {
      NewsAdapterConstant.LOADING
    } else {
      if (position % CLUSTER_SIZE == 0) NewsAdapterConstant.ONE_ROW_ARTICLE else NewsAdapterConstant.NORMAL_ARTICLE
    }
  }

  fun updateProgressState(newNetworkState: NetworkState?) {
    if (currentList != null) {
      if (currentList!!.isNotEmpty()) {
        this.networkState = newNetworkState
        notifyDataSetChanged()
      }
    }
  }

}
