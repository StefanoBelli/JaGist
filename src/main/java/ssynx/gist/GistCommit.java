package ssynx.gist;

import org.json.JSONException;
import org.json.JSONObject;

public class GistCommit {

    private final String fullGistJson;

    private GistOwner user;
    private final String version;
    private final String committedAt;
    private final GistChangeStatus changeStatus;
    private final String url;

    GistCommit(final String fullJson) {
        fullGistJson = fullJson;

        final JSONObject gistObject = new JSONObject(fullJson);
        version = gistObject.getString("version");
        committedAt = gistObject.getString("committed_at");
        changeStatus = new GistChangeStatus(gistObject.getJSONObject("change_status"));
        url = gistObject.getString("url");

        try {
            user = new GistOwner(gistObject.getJSONObject("user"));
        } catch(JSONException noSuchUser) {
            user = null;
        }
    }

    public String getVersion() {
        return version;
    }

    public GistChangeStatus getChangeStatus() {
        return changeStatus;
    }

    public GistOwner getUser() {
        return user;
    }

    public String getCommittedAt() {
        return committedAt;
    }

    public String getUrl() {
        return url;
    }

    @Override
    public String toString() {
        return fullGistJson;
    }
}
