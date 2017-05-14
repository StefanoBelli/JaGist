package ssynx.gist;

import org.json.JSONObject;
import java.math.BigInteger;

public class GistFile {

    private final String jsonPiece;

    private final BigInteger size;
    private final String rawUrl;
    private final String type;
    //private final boolean truncated;
    //private final String language;

    public GistFile(final JSONObject fileObject) {
        jsonPiece = fileObject.toString();

        size = fileObject.getBigInteger("size");
        rawUrl = fileObject.getString("raw_url");
        type = fileObject.getString("type");
        //truncated = fileObject.getBoolean("truncated");
        //language = fileObject.getString("language");
    }

    @Override
    public String toString() {
        return jsonPiece;
    }

    public BigInteger getSize() {
        return size;
    }

    /*public String getLanguage() {
        return language;
    }*/

    public String getRawUrl() {
        return rawUrl;
    }

    public String getType() {
        return type;
    }
}
