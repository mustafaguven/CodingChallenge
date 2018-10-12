package com.mguven.bonial

import android.content.Context
import android.content.Intent
import com.mguven.bonial.constant.BundleKey
import com.mguven.bonial.model.Article
import com.mguven.bonial.ui.ArticleDetailActivity

class FlowController {

  companion object {
    fun launchArticleDetailActivity(context: Context, article: Article) {
      context.startActivity(
          Intent(context, ArticleDetailActivity::class.java)
              .putExtra(BundleKey.ARTICLE, article)
      )
    }

  }

}