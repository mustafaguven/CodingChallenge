package com.mguven.bonial.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.TextView

import com.mguven.bonial.R
import com.mguven.bonial.common.ViewType
import com.mguven.bonial.common.ViewTypeDelegateAdapter
import com.mguven.bonial.model.Article

class LoadingDelegateAdapter : ViewTypeDelegateAdapter {

  override fun onCreateViewHolder(parent: ViewGroup): androidx.recyclerview.widget.RecyclerView.ViewHolder {
    return LoadingViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.news_loading, parent, false))
  }

  override fun onBindViewHolder(holder: androidx.recyclerview.widget.RecyclerView.ViewHolder, item: ViewType) {
    val layoutParams = holder.itemView.layoutParams as androidx.recyclerview.widget.StaggeredGridLayoutManager.LayoutParams
    layoutParams.isFullSpan = true
  }

  private inner class LoadingViewHolder(itemView: View) : androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView)
}
