package com.mguven.bonial.di

import androidx.lifecycle.ViewModel

import com.mguven.bonial.viewmodel.NewsViewModel

import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

  @Binds
  @IntoMap
  @ViewModelKey(NewsViewModel::class)
  abstract fun bindListViewModel(viewModel: NewsViewModel): ViewModel

}
