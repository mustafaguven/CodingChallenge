package com.mguven.bonial.di.component

import com.mguven.bonial.ui.AbstractBaseActivity
import com.mguven.bonial.di.module.ActivityModule

import dagger.Subcomponent

@Subcomponent(modules = arrayOf(ActivityModule::class))
interface AbstractBaseComponent {

  fun inject(activity: AbstractBaseActivity)
}
