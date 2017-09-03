package ssynx.gist;

import org.json.JSONException;
import org.json.JSONObject;

/*!
 * @brief object representing a gist file (methods are self-explaining)
 */
public class GistFile {

    private final String jsonPiece;

    private final int size;
    private final String rawUrl;
    private final String type;
    private boolean truncated;
    private final Object language;

    GistFile(final JSONObject fileObject) {
        jsonPiece = fileObject.toString();

        size = fileObject.getInt("size");
        rawUrl = fileObject.getString("raw_url");
        type = fileObject.getString("type");
        try {
            truncated = fileObject.getBoolean("truncated");
        } catch(JSONException noSuchTruncation) {
            truncated = false;
        }
        language = fileObject.get("language");
    }

    @Override
    public String toString() {
        return jsonPiece;
    }

    public int getSize() {
        return size;
    }

    public Object getLanguage() {
        return language;
    }

    public String getRawUrl() {
        return rawUrl;
    }

    public String getType() {
        return type;
    }

    public boolean getTruncated() {
        return truncated;
    }
}
