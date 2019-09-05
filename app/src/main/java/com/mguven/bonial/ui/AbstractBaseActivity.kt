package com.mguven.bonial.ui

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.mguven.bonial.BonialApplication
import com.mguven.bonial.di.ViewModelFactory
import com.mguven.bonial.di.component.AbstractBaseComponent
import com.mguven.bonial.di.module.ActivityModule
import com.squareup.picasso.Picasso
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class AbstractBaseActivity : AppCompatActivity(), LifecycleObserver {

  @Inject
  lateinit var viewModelFactory: ViewModelFactory

  @Inject
  lateinit var compositeDisposable: CompositeDisposable

  private var component: AbstractBaseComponent? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

    component = (application as BonialApplication)
        .applicationComponent
        .plus(ActivityModule(this))

    component!!.inject(this)


  }

  protected fun <T : ViewModel> getViewModel(viewModelClz: Class<T>): T =
      ViewModelProviders.of(this, viewModelFactory).get(viewModelClz)

  override fun onStop() {
    super.onStop()
    if (!compositeDisposable.isDisposed) {
      compositeDisposable.dispose()
    }
  }
}
