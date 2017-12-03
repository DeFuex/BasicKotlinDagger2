package at.defuex.basickotlindagger2.ui.main.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import at.defuex.basickotlindagger2.R
import at.defuex.basickotlindagger2.model.GithubFollower
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide

/**
 * Created by timoobereder on 03.12.17.
 */

class GithubFollowerAdapter(private val context: Context, private val followers: List<GithubFollower>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when(holder) {
            is GithubFollowerViewHolder -> holder.bindResult(followers[position], position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder? {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.item_follower, parent, false) as ViewGroup
        return GithubFollowerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return followers.size
    }

    class GithubFollowerViewHolder(internal var view: View) : RecyclerView.ViewHolder(view) {

        @BindView(R.id.name)
        lateinit var name: TextView

        @BindView(R.id.url)
        lateinit var url: TextView

        @BindView(R.id.image)
        lateinit var image: ImageView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bindResult(follower: GithubFollower, position: Int) {
            with(itemView) {
                name.text = follower.login
                url.text = follower.avatarUrl

                Glide.with(context)
                        .load(follower.avatarUrl)
                        .into(image)
            }
        }
    }
}