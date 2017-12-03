package at.defuex.basickotlindagger2.ui.base.view

import android.os.Bundle
import at.defuex.basickotlindagger2.ui.base.presenter.Presenter
import javax.inject.Inject

/**
 * Created by timoobereder on 03.12.17.
 */

// This view defines when a presenter is set dependend on the Android lifecycle
abstract class BaseViewFragment<T : Presenter> : BaseFragment(), MVPView {

    @Inject
    lateinit var presenter: T

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        // start the presenter here
        // reason: we want to make sure that the view is bound here
        presenter.onStart(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onPause() {
        super.onPause()
        presenter.onPause()
    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        presenter.onSaveInstanceState(outState)
    }

    override fun onDestroyView() {
        presenter.onEnd()
        super.onDestroyView()
    }
}