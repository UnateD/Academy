package com.unated.academy.adapter

import androidx.recyclerview.widget.RecyclerView

fun <T : RecyclerView.ViewHolder> T.listener(event: (Int) -> Unit): T {
    itemView.setOnClickListener { event.invoke(adapterPosition) }
    return this
}