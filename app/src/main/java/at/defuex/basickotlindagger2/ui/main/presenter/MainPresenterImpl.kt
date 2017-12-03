package at.defuex.basickotlindagger2.ui.main.presenter

import at.defuex.basickotlindagger2.data.RestManager
import at.defuex.basickotlindagger2.di.inject.PerFragment
import at.defuex.basickotlindagger2.model.GithubFollower
import at.defuex.basickotlindagger2.ui.base.presenter.BasePresenter
import at.defuex.basickotlindagger2.ui.main.view.MainView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.internal.disposables.ArrayCompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * Created by timoobereder on 03.12.17.
 */

// Add RestManager in constructor later!
@PerFragment
internal class MainPresenterImpl @Inject
constructor(view: MainView, private val compositeDisposable: CompositeDisposable, private val restManager: RestManager) : BasePresenter<MainView>(view), MainPresenter {
    override fun getGithubFollowers(name: String) {
        restManager.getGithubFollowers(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object: DisposableObserver<ArrayList<GithubFollower>>() {
                    override fun onError(e: Throwable) {
                        view.onNetworkError(e.message!!)
                    }

                    override fun onComplete() {
                        // do something if request has been complete
                    }

                    override fun onNext(objects: ArrayList<GithubFollower>) {
                        // get first or ongoing results and do something
                        view.onFollowersLoaded(objects)
                    }

                })

        // compositeDisposable.add(disposable)
        // TODO: implement an rx observable client (maybe as util class) to dispose an added observable from anywhere
    }
}