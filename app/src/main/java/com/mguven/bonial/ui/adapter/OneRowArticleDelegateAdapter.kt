package com.mguven.bonial.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.mguven.bonial.FlowController
import com.mguven.bonial.R
import com.mguven.bonial.common.ViewType
import com.mguven.bonial.common.ViewTypeDelegateAdapter
import com.mguven.bonial.extension.findBy
import com.mguven.bonial.extension.hoursAgo
import com.mguven.bonial.extension.loadUrl
import com.mguven.bonial.model.Article


class OneRowArticleDelegateAdapter : ViewTypeDelegateAdapter {

  override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
    return ExpandedArticleViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.expanded_article_item, parent, false))
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    val layoutParams = holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams
    layoutParams.isFullSpan = true
    (holder as OneRowArticleDelegateAdapter.ExpandedArticleViewHolder).bind(item as Article)
  }

  private inner class ExpandedArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(article: Article) {
      itemView.findBy(R.id.tvTitle, TextView::class.java).text = article.title
      itemView.findBy(R.id.tvDate, TextView::class.java).text = article.publishedAt?.hoursAgo()
      itemView.findBy(R.id.tvContent, TextView::class.java).text = article.content
      itemView.findBy(R.id.tvFrom, TextView::class.java).text = article.author ?: ""
      itemView.findBy(R.id.image, ImageView::class.java).loadUrl(article.urlToImage)
      itemView.findBy(R.id.tvTitle, TextView::class.java).text = article.title
      itemView.findBy(R.id.lnMain, LinearLayout::class.java).setOnClickListener {
        FlowController.launchArticleDetailActivity(itemView.context, article)
      }
    }

  }
}
