package ssynx.gist;

import org.json.JSONObject;

public class GistComment {

    private final String jsonStr;

    private final int id;
    private final String url;
    private final String body;
    private final GistOwner user;
    private final String createdAt;
    private final String updatedAt;

    GistComment(final JSONObject obj) {
        jsonStr = obj.toString();

        id = obj.getInt("id");
        url = obj.getString("url");
        body = obj.getString("body");
        user = new GistOwner(obj.getJSONObject("user"));
        createdAt = obj.getString("created_at");
        updatedAt = obj.getString("updated_at");
    }

    @Override
    public String toString() {
        return jsonStr;
    }

    public String getUrl() {
        return url;
    }

    public GistOwner getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }
}
