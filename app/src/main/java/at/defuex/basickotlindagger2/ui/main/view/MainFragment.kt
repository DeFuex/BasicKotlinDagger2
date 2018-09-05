package at.defuex.basickotlindagger2.ui.main.view

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import at.defuex.basickotlindagger2.R
import at.defuex.basickotlindagger2.model.GithubFollower
import at.defuex.basickotlindagger2.ui.base.view.BaseViewFragment
import at.defuex.basickotlindagger2.ui.main.adapter.GithubFollowerAdapter
import at.defuex.basickotlindagger2.ui.main.presenter.MainPresenter
import butterknife.BindView
import retrofit2.Response


/**
 * Created by timoobereder on 03.12.17.
 */
class MainFragment : BaseViewFragment<MainPresenter>(), MainView {

    @BindView(R.id.recyclerView)
    lateinit var recyclerView: RecyclerView

    lateinit var adapter: GithubFollowerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View =
            inflater.inflate(R.layout.fragment_main, container, false)

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)

        presenter.getGithubFollowers("DeFuex")
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
    }

    override fun onFollowersLoaded(objects: ArrayList<GithubFollower>?) {
//        this.activity.runOnUiThread {
            adapter = GithubFollowerAdapter(activityContext, objects)
            recyclerView.adapter = adapter
//        }
    }

    override fun onNetworkError(error: String) {
        //looks okay :P
        Toast.makeText(this.activity, "Error: " + error, Toast.LENGTH_LONG).show()
    }
}