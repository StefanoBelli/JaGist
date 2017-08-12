package ssynx.gist;

import org.json.JSONObject;

/*!
 * @brief object representing a fork
 */
public class GistFork {

    private final String jsonPiece;

    private final GistOwner user;
    private final String url;
    private final String id;
    private final String createdAt;
    private final String updatedAt;

    GistFork(final JSONObject forkObject) {
        jsonPiece = forkObject.toString();

        user = new GistOwner(forkObject.getJSONObject("user"));
        url = forkObject.getString("url");
        id = forkObject.getString("id");
        createdAt = forkObject.getString("created_at");
        updatedAt = forkObject.getString("updated_at");
    }

    @Override
    public String toString() {
        return jsonPiece;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getId() {
        return id;
    }

    public GistOwner getUser() {
        return user;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUrl() {
        return url;
    }
}
