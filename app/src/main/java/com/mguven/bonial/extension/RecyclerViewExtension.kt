package com.mguven.bonial.extension

import android.content.res.Configuration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager


fun RecyclerView.loadLayoutManager() {
  val layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
  this.layoutManager = layoutManager
}

fun RecyclerView.loadLayoutManagerInBonialStyle() {
  val spanCount = if (this.context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
  val layoutManager = StaggeredGridLayoutManager(spanCount, StaggeredGridLayoutManager.VERTICAL)

  this.layoutManager = layoutManager
}