package at.defuex.basickotlindagger2.model

import com.google.gson.annotations.SerializedName

/**
 * Created by timoobereder on 03.12.17.
 */
data class GithubFollower (

        @SerializedName("login")
        val login: String,
        @SerializedName("id")
        val id: Int,
        @SerializedName("avatar_url")
        val avatarUrl: String,
        @SerializedName("gravatar_id")
        var gravatarId: String,
        @SerializedName("url")
        var url: String,
        @SerializedName("html_url")
        var htmlUrl: String,
        @SerializedName("followers_url")
        var followersUrl: String,
        @SerializedName("following_url")
        var followingUrl: String,
        @SerializedName("gists_url")
        var gistsUrl: String,
        @SerializedName("starred_url")
        var starredUrl: String,
        @SerializedName("subscriptions_url")
        var subscriptionsUrl: String,
        @SerializedName("organizations_url")
        var organizationsUrl: String,
        @SerializedName("repos_url")
        var reposUrl: String,
        @SerializedName("events_url")
        var eventsUrl: String,
        @SerializedName("received_events_url")
        var receivedEventsUrl: String,
        @SerializedName("type")
        var type: String,
        @SerializedName("site_admin")
        var siteAdmin: Boolean
)