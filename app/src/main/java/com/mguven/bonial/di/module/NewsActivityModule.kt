package com.mguven.bonial.di.module

import com.mguven.bonial.ui.AbstractBaseActivity
import com.mguven.bonial.ui.adapter.NewsAdapter
import dagger.Module
import dagger.Provides

@Module
class NewsActivityModule(activity: AbstractBaseActivity) : ActivityModule(activity) {

  @Provides
  fun provideAdapter(): NewsAdapter {
    return NewsAdapter()
  }
}
