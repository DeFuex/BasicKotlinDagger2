package at.defuex.basickotlindagger2.data.network

import android.content.Context
import at.defuex.basickotlindagger2.data.RestManager
import at.defuex.basickotlindagger2.di.inject.ApplicationContext
import at.defuex.basickotlindagger2.model.GithubFollower
import io.reactivex.Observable
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
//import kotlinx.coroutines.experimental.Deferred
import javax.inject.Inject

/**
 * Created by timoobereder on 03.12.17.
 */

class RestManagerImpl @Inject
constructor(@param:ApplicationContext private val context: Context, private val restInterface: RestInterface) : RestManager {
    override fun getGithubFollowers(name: String): Deferred<Response<ArrayList<GithubFollower>>> =
            restInterface.getFollowers(name)
//
//    override fun getGithubFollowers(name: String): Observable<ArrayList<GithubFollower>> =
//            restInterface.getFollowers(name)

}