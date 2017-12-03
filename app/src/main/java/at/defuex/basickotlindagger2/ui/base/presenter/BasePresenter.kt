package at.defuex.basickotlindagger2.ui.base.presenter

import android.os.Bundle
import at.defuex.basickotlindagger2.ui.base.view.MVPView

/**
 * Created by timoobereder on 03.12.17.
 */

abstract class BasePresenter<out T : MVPView> protected constructor(protected val view: T) : Presenter {

    override fun onStart(savedInstanceState: Bundle?) {}

    override fun onResume() {}

    override fun onPause() {}

    override fun onSaveInstanceState(outState: Bundle) {}

    override fun onEnd() {}
}