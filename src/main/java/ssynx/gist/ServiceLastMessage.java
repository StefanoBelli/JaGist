package ssynx.gist;

import org.json.JSONObject;

/*!
 * @brief object representing last message (refer to GithubStatus class)
 */
public class ServiceLastMessage {

    private final String fullJson;

    private final String status;
    private final String body;
    private final String createdOn;

    ServiceLastMessage(JSONObject statusObj) {
        fullJson = statusObj.toString();
        status = statusObj.getString("status");
        body = statusObj.getString("body");
        createdOn = statusObj.getString("created_on");
    }

    @Override
    public String toString() {
        return fullJson;
    }

    public String getStatus() {
        return status;
    }

    public String getBody() {
        return body;
    }

    public String getCreatedOn() {
        return createdOn;
    }
}
