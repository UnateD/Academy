package com.unated.academy

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun AppCompatActivity.replaceFragment(container: Int, fragment: Fragment) = supportFragmentManager.beginTransaction().addToBackStack(null).replace(container, fragment).commit()

fun AppCompatActivity.addFragment(container: Int, fragment: Fragment) = supportFragmentManager.beginTransaction().add(container, fragment).commit()