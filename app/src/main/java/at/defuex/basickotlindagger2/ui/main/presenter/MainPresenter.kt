package at.defuex.basickotlindagger2.ui.main.presenter

import at.defuex.basickotlindagger2.ui.base.presenter.Presenter

/**
 * Created by timoobereder on 03.12.17.
 */

interface MainPresenter : Presenter {

    fun getGithubFollowers(name: String)
}