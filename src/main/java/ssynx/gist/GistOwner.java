package ssynx.gist;

import org.json.JSONObject;

/*!
 * @brief Object representing Gist owner (methods are self-explaining)
 */
public class GistOwner {

    private final String jsonPiece;

    private final String login;
    private final int id;
    private final String avatarUrl;
    private final String gravatarId;
    private final String url;
    private final String htmlUrl;
    private final String followersUrl;
    private final String followingUrl;
    private final String gistsUrl;
    private final String starredUrl;
    private final String subscriptionsUrl;
    private final String organizationsUrl;
    private final String reposUrl;
    private final String eventsUrl;
    private final String receivedEventsUrl;
    private final String type;
    private final boolean siteAdmin;

    GistOwner(final JSONObject ownerObject) {
        jsonPiece = ownerObject.toString();

        login = ownerObject.getString("login");
        id = ownerObject.getInt("id");
        avatarUrl = ownerObject.getString("avatar_url");
        gravatarId = ownerObject.getString("gravatar_id");
        url = ownerObject.getString("url");
        htmlUrl = ownerObject.getString("html_url");
        followersUrl = ownerObject.getString("followers_url");
        followingUrl = ownerObject.getString("following_url");
        gistsUrl = ownerObject.getString("gists_url");
        starredUrl = ownerObject.getString("starred_url");
        subscriptionsUrl = ownerObject.getString("subscriptions_url");
        organizationsUrl = ownerObject.getString("organizations_url");
        reposUrl = ownerObject.getString("repos_url");
        eventsUrl = ownerObject.getString("events_url");
        receivedEventsUrl = ownerObject.getString("received_events_url");
        type = ownerObject.getString("type");
        siteAdmin = ownerObject.getBoolean("site_admin");
    }

    @Override
    public String toString() {
        return jsonPiece;
    }

    public int getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getEventsUrl() {
        return eventsUrl;
    }

    public String getFollowersUrl() {
        return followersUrl;
    }

    public String getFollowingUrl() {
        return followingUrl;
    }

    public String getGistsUrl() {
        return gistsUrl;
    }

    public String getGravatarId() {
        return gravatarId;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public String getLogin() {
        return login;
    }

    public String getOrganizationsUrl() {
        return organizationsUrl;
    }

    public String getReceivedEventsUrl() {
        return receivedEventsUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }

    public String getStarredUrl() {
        return starredUrl;
    }

    public String getSubscriptionsUrl() {
        return subscriptionsUrl;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public boolean getSiteAdmin() {
        return siteAdmin;
    }
}
