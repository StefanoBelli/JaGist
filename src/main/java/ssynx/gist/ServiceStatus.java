package ssynx.gist;

import org.json.JSONObject;

/*!
 * @brief object representing last status update (refer to GithubStatus class)
 */
public class ServiceStatus {

    private final String fullJson;

    private final String status;
    private final String lastUpdated;

    ServiceStatus(JSONObject statusObj) {
        fullJson = statusObj.toString();
        status = statusObj.getString("status");
        lastUpdated = statusObj.getString("last_updated");
    }

    @Override
    public String toString() {
        return fullJson;
    }

    public String getStatus() {
        return status;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
}
