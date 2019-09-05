package com.mguven.bonial.extension

import android.content.res.Configuration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager


fun androidx.recyclerview.widget.RecyclerView.loadLayoutManager() {
  val layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this.context, androidx.recyclerview.widget.LinearLayoutManager.VERTICAL, false)
  this.layoutManager = layoutManager
}

fun androidx.recyclerview.widget.RecyclerView.loadLayoutManagerInBonialStyle() {
  val spanCount = if (this.context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) 2 else 3
  val layoutManager = androidx.recyclerview.widget.StaggeredGridLayoutManager(spanCount, androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL)

  this.layoutManager = layoutManager
}