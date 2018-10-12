package com.mguven.bonial.di.component

import com.mguven.bonial.BonialApplication
import com.mguven.bonial.di.ViewModelModule
import com.mguven.bonial.di.module.*

import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = [
  ApplicationModule::class,
  NetworkModule::class,
  ViewModelModule::class,
  SchedulerModule::class
])
interface ApplicationComponent {

  fun inject(bonialApplication: BonialApplication)

  operator fun plus(activityModule: ActivityModule): AbstractBaseComponent

  operator fun plus(mainActivityModule: NewsActivityModule): NewsActivityComponent
}
