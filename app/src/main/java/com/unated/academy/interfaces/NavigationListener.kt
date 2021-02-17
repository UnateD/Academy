package com.unated.academy.interfaces

import android.view.View

interface NavigationListener {

    fun goToDetails(id: Int, sharedView: View?)

    fun goToMain()

}