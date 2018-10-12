package com.mguven.bonial.ui

import android.os.Bundle
import com.mguven.bonial.R
import com.mguven.bonial.constant.BundleKey
import com.mguven.bonial.extension.hoursAgo
import com.mguven.bonial.extension.loadUrl
import com.mguven.bonial.model.Article
import kotlinx.android.synthetic.main.expanded_article_item.*

class ArticleDetailActivity : AbstractBaseActivity() {

  val article by lazy {
    (intent.getSerializableExtra(BundleKey.ARTICLE) as Article)
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_article_detail)

    image.loadUrl(article.urlToImage)
    tvTitle.text = article.title
    tvDate.text = article.publishedAt?.hoursAgo()
    tvContent.text = article.content
    tvFrom.text = article.author
  }

}