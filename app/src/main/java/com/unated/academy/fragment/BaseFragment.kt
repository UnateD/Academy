package com.unated.academy.fragment

import android.content.Context
import androidx.fragment.app.Fragment
import com.unated.academy.DataProvider

open class BaseFragment : Fragment() {

    var dataProvider: DataProvider? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val ctx = context.applicationContext
        if(ctx is DataProvider) {
            dataProvider = ctx
        }
    }

    override fun onDetach() {
        dataProvider = null
        super.onDetach()
    }
}