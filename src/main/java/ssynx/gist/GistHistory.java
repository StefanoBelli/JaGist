package ssynx.gist;

import org.json.JSONObject;

public class GistHistory {

    private final String jsonPiece;

    private final String url;
    private final String version;
    private final GistOwner user;
    private final String commitedAt;
    private final GistChangeStatus changeStatus;

    public GistHistory(final JSONObject historyObject) {
        jsonPiece = historyObject.toString();
        url = historyObject.getString("url");
        version = historyObject.getString("version");
        user = new GistOwner(historyObject.getJSONObject("user"));
        commitedAt = historyObject.getString("commited_at");
        changeStatus = new GistChangeStatus(historyObject
                .getJSONObject("change_status"));
    }

    @Override
    public String toString() {
        return jsonPiece;
    }

    public String getUrl() {
        return url;
    }

    public GistOwner getUser() {
        return user;
    }

    public GistChangeStatus getChangeStatus() {
        return changeStatus;
    }

    public String getCommitedAt() {
        return commitedAt;
    }

    public String getVersion() {
        return version;
    }
}
