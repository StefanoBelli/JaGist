package ssynx.gist;

import org.json.JSONObject;

/*!
 * @brief object representing version of gist
 */
public class GistHistory {

    private final String jsonPiece;

    private final String url;
    private final String version;
    private final GistOwner user;
    private final String committedAt;
    private final GistChangeStatus changeStatus;

    GistHistory(final JSONObject historyObject) {
        jsonPiece = historyObject.toString();
        url = historyObject.getString("url");
        version = historyObject.getString("version");
        user = new GistOwner(historyObject.getJSONObject("user"));
        committedAt = historyObject.getString("committed_at");
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

    public String getCommittedAt() {
        return committedAt;
    }

    public String getVersion() {
        return version;
    }
}
