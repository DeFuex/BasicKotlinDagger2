package at.defuex.basickotlindagger2.data.network

import at.defuex.basickotlindagger2.model.GithubFollower
import io.reactivex.Observable
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
//import kotlinx.coroutines.experimental.Deferred
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by timoobereder on 03.12.17.
 */

interface RestInterface {

    @GET("users/{username}/followers")
//    fun getFollowers(@Path("username") username: String): Observable<ArrayList<GithubFollower>>
    fun getFollowers(@Path("username") username: String): Deferred<Response<ArrayList<GithubFollower>>>
}