package at.defuex.basickotlindagger2.data

import at.defuex.basickotlindagger2.model.GithubFollower
import io.reactivex.Observable

/**
 * Created by timoobereder on 03.12.17.
 */

interface RestManager {

    fun getGithubFollowers(name: String): Observable<ArrayList<GithubFollower>>
}