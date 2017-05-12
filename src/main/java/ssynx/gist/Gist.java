package ssynx.gist;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Gist {

    private final String fullGistJson;

    private final String url;
    private final String forksUrl;
    private final String commitsUrl;
    private final String id;
    private final String description;
    private final boolean isPublic;
    private final String user;
    private final String commentsUrl;
    private final int comments;
    private final String htmlUrl;
    private final String gitPullUrl;
    private final String gitPushUrl;
    private final String createdAt;
    private final String updatedAt;
    private Set<GistHistory> history;
    private Set<GistFork> forks;
    private final GistOwner owner;
    private final Map<String,GistFile> files;

    public Gist(final String fullJson) {
        fullGistJson = fullJson;
        final JSONObject gistObject = new JSONObject(fullJson);
        url = gistObject.getString("url");
        forksUrl = gistObject.getString("forks_url");
        commitsUrl = gistObject.getString("commits_url");
        id = gistObject.getString("id");
        description = gistObject.getString("description");
        isPublic = gistObject.getBoolean("public");
        user = gistObject.getString("user");
        commentsUrl = gistObject.getString("comments_url");
        comments = gistObject.getInt("comments");
        htmlUrl = gistObject.getString("html_url");
        gitPullUrl = gistObject.getString("git_pull_url");
        gitPushUrl = gistObject.getString("git_push_url");
        createdAt = gistObject.getString("created_at");
        updatedAt = gistObject.getString("updated_at");
        owner = new GistOwner(gistObject.getJSONObject("owner"));
        files = new HashMap<>();
        final JSONObject filesObject = gistObject.getJSONObject("files");
        for(final String key : filesObject.keySet())
            files.put(key,new GistFile(filesObject.getJSONObject(key)));

        try {
            JSONArray forksArray = gistObject.getJSONArray("forks");
            forks = new HashSet<>();
            for(int i=0;i<forksArray.length();i++)
                forks.add(new GistFork(
                        (JSONObject)forksArray.get(i)));
        } catch(JSONException noSuchFork) {
            forks = null;
        }

        try {
            JSONArray historyArray = gistObject.getJSONArray("history");
            history = new HashSet<>();
            for(int i=0;i<historyArray.length();i++)
                history.add(new GistHistory(
                        (JSONObject)historyArray.get(i)));
        } catch(JSONException noSuchHistory) {
            history = null;
        }
    }

    @Override
    public String toString() {
        return fullGistJson;
    }

    public String getUrl() {
        return url;
    }

    public GistOwner getOwner() {
        return owner;
    }

    public int getComments() {
        return comments;
    }

    public Map<String, GistFile> getFiles() {
        return files;
    }

    public String getCommentsUrl() {
        return commentsUrl;
    }

    public String getCommitsUrl() {
        return commitsUrl;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getDescription() {
        return description;
    }

    public String getForksUrl() {
        return forksUrl;
    }

    public String getFullGistJson() {
        return fullGistJson;
    }

    public String getGitPullUrl() {
        return gitPullUrl;
    }

    public String getGitPushUrl() {
        return gitPushUrl;
    }

    public String getId() {
        return id;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getUser() {
        return user;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public Set<GistFork> getForks() {
        return forks;
    }

    public Set<GistHistory> getHistory() {
        return history;
    }
}
