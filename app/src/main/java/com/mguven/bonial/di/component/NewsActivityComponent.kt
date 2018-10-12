package com.mguven.bonial.di.component

import com.mguven.bonial.ui.NewsActivity
import com.mguven.bonial.di.module.NewsActivityModule

import dagger.Subcomponent

@Subcomponent(modules = [NewsActivityModule::class])
interface NewsActivityComponent {
  fun inject(newsActivity: NewsActivity)
}
