package com.mguven.bonial.di.module

import android.content.Context
import android.view.LayoutInflater
import com.mguven.bonial.di.scope.PerActivity
import com.mguven.bonial.ui.AbstractBaseActivity
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable

@Module
open class ActivityModule(val activity: AbstractBaseActivity) {

  @PerActivity
  @Provides
  fun provideContext(): Context = activity

  @Provides
  fun providesLayoutInflater(): LayoutInflater = LayoutInflater.from(activity)

  @Provides
  fun providesCompositeDisposable(): CompositeDisposable = CompositeDisposable()

}
