package com.unated.academy.presentation

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

fun <T : RecyclerView.ViewHolder> T.itemViewClickListener(event: (Int) -> Unit): T {
    itemView.setOnClickListener { event.invoke(adapterPosition) }
    return this
}

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context