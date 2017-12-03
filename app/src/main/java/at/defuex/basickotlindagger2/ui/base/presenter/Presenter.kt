package at.defuex.basickotlindagger2.ui.base.presenter

import android.os.Bundle

/**
 * Created by timoobereder on 03.12.17.
 */

interface Presenter {

    fun onStart(savedInstanceState: Bundle?)

    fun onResume()

    fun onPause()

    fun onSaveInstanceState(outState: Bundle)

    fun onEnd()
}