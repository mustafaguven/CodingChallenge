package com.mguven.bonial.ui.adapter

import android.app.ActionBar
import android.support.v7.widget.RecyclerView
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


class ArticleDelegateAdapter : ViewTypeDelegateAdapter {

  override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
    return ArticleHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.article_item, parent, false))
  }

  override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
    (holder as ArticleHolder).bind(item as Article)
  }

  private inner class ArticleHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(article: Article) {
      itemView.findBy(R.id.tvTitle, TextView::class.java).text = article.title
      itemView.findBy(R.id.tvDate, TextView::class.java).text = article.publishedAt?.hoursAgo()
      itemView.findBy(R.id.tvDescription, TextView::class.java).text = article.description
      itemView.findBy(R.id.tvFrom, TextView::class.java).text = article.author ?: ""
      itemView.findBy(R.id.image, ImageView::class.java).loadUrl(article.urlToImage)

      itemView.findBy(R.id.lnMain, LinearLayout::class.java).setOnClickListener {
        FlowController.launchArticleDetailActivity(itemView.context, article)
      }
    }

  }

}
