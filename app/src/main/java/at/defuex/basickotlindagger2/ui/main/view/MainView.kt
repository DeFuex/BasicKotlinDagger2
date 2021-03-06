package at.defuex.basickotlindagger2.ui.main.view

import at.defuex.basickotlindagger2.model.GithubFollower
import at.defuex.basickotlindagger2.ui.base.view.MVPView
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

/**
 * Created by timoobereder on 03.12.17.
 */

interface MainView : MVPView {

    fun onNetworkError(error: String)

    fun onFollowersLoaded(objects: ArrayList<GithubFollower>?)
}