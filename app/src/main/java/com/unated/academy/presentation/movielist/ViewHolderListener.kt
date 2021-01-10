package com.unated.academy.presentation.movielist

import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> T.listener(event: (Int) -> Unit): T {
    itemView.setOnClickListener { event.invoke(adapterPosition) }
    return this
}

fun RecyclerView.ViewHolder.listener2(event: (Int)-> Unit) {
    itemView.setOnClickListener { event.invoke(adapterPosition) }
}