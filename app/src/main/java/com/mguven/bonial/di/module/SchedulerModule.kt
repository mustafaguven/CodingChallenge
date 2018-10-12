package com.mguven.bonial.di.module

import com.mguven.bonial.rx.AppSchedulerProvider
import com.mguven.bonial.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class SchedulerModule {

  @Provides
  @Singleton
  fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

}