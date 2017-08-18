package ssynx.gist;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

/*!
 * @brief Class containing static methods to fetch GitHub API status
 */
public class GithubStatus {

    /*!
     * @brief get last status update
     * @return ServiceStatus object representing GitHub status
     */
    public static ServiceStatus getStatus()
            throws JaGistException {

        String jsonStr;
        try {
            jsonStr = JaGistHttps.getApiStatus("status");
        } catch(IOException ioe) {
            throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                    JaGistHttps.getLastCode());
        }

        return new ServiceStatus(new JSONObject(jsonStr));
    }

    /*!
     * @brief get last human message from GitHub staff
     * @return ServiceLastMessage object representing the message/status
     */
    public static ServiceLastMessage getLastMessage()
            throws JaGistException {

        String jsonStr;
        try {
            jsonStr = JaGistHttps.getApiStatus("last-message");
        } catch(IOException ioe) {
            throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                    JaGistHttps.getLastCode());
        }

        return new ServiceLastMessage(new JSONObject(jsonStr));
    }

    /*!
     * @brief get last human MESSAGES from GitHub staff
     * @return ServiceLastMessage array representing the messages
     */
    public static ServiceLastMessage[] getMessages()
            throws JaGistException {

        String jsonStr;
        try {
            jsonStr = JaGistHttps.getApiStatus("messages");
        } catch(IOException ioe) {
            throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                    JaGistHttps.getLastCode());
        }

        JSONArray messages = new JSONArray(jsonStr);
        int lenMessages = messages.length();
        ServiceLastMessage[] messagesArr = new ServiceLastMessage[lenMessages];

        for(int i=0;i<lenMessages;i++)
            messagesArr[i] = new ServiceLastMessage(messages.getJSONObject(i));

        return messagesArr;
    }
}
