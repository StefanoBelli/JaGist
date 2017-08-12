package ssynx.gist;

import org.json.JSONObject;

/*!
 * @brief object representing gist change status
 */
public class GistChangeStatus {

    private final String jsonPiece;

    private final int deletions;
    private final int additions;
    private final int total;

    GistChangeStatus(final JSONObject changeStatusObject) {
        jsonPiece = changeStatusObject.toString();
        deletions = changeStatusObject.getInt("deletions");
        additions = changeStatusObject.getInt("additions");
        total = changeStatusObject.getInt("total");
    }

    @Override
    public String toString() {
        return jsonPiece;
    }

    public int getAdditions() {
        return additions;
    }

    public int getDeletions() {
        return deletions;
    }

    public int getTotal() {
        return total;
    }
}
