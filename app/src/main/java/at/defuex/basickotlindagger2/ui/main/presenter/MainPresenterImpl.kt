package at.defuex.basickotlindagger2.ui.main.presenter

import at.defuex.basickotlindagger2.data.RestManager
import at.defuex.basickotlindagger2.di.inject.PerFragment
import at.defuex.basickotlindagger2.ui.base.presenter.BasePresenter
import at.defuex.basickotlindagger2.ui.main.view.MainView
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

/**
 * Created by timoobereder on 03.12.17.
 */

// Add RestManager in constructor later!
@PerFragment
internal class MainPresenterImpl @Inject
constructor(view: MainView, private val compositeDisposable: CompositeDisposable, private val restManager: RestManager) : BasePresenter<MainView>(view), MainPresenter {

    override fun getGithubFollowers(name: String) {

        async(UI) {
            try {
                val request = restManager.getGithubFollowers(name)
                val response = request.await()
                if ( response.isSuccessful ) {
                    view.onFollowersLoaded(response.body())
                }
            } catch (e: Exception) {
                view.onNetworkError(e.toString())
            }
        }
    }
}