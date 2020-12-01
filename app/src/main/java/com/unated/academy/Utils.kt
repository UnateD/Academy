package com.unated.academy

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

fun AppCompatActivity.replaceFragment(container: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().addToBackStack(null).replace(container, fragment)
        .commit()

fun AppCompatActivity.addFragment(container: Int, fragment: Fragment) =
    supportFragmentManager.beginTransaction().add(container, fragment).commit()

val RecyclerView.ViewHolder.context: Context
    get() = this.itemView.context