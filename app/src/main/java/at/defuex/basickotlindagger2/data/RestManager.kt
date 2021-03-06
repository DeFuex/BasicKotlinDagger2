package at.defuex.basickotlindagger2.data

import at.defuex.basickotlindagger2.model.GithubFollower
import io.reactivex.Observable
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response

/**
 * Created by timoobereder on 03.12.17.
 */

interface RestManager {

//    fun getGithubFollowers(name: String): Observable<ArrayList<GithubFollower>>
    fun getGithubFollowers(name: String): Deferred<Response<ArrayList<GithubFollower>>>
}
