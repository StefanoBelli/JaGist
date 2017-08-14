package ssynx.gist;

import org.json.JSONObject;

/*!
 * @brief object used to edit a gist, refer to: JaGist.PerformGist.edit() method
 */
public class EditGist {

    private final JSONObject editJson;

    public EditGist(final String description) {
        final JSONObject files = new JSONObject();
        editJson = new JSONObject();
        editJson.put("files",files);
        editJson.put("description",description);
    }

    public EditGist() {
        final JSONObject files = new JSONObject();
        editJson = new JSONObject();
        editJson.put("files",files);
    }

    public void deleteFile(final String whichFile) {
        editJson.getJSONObject("files")
                .put(whichFile,JSONObject.NULL);
    }

    public void editFile(final String whichFile, final String rename,
                         final String content) {
        final JSONObject fileObject = new JSONObject();
        fileObject.put("content",content);
        fileObject.put("filename",rename);

        editJson.getJSONObject("files")
                .put(whichFile,fileObject);
    }

    public void editFileName(final String whichFile, final String rename) {
        final JSONObject fileObject = new JSONObject();
        fileObject.put("filename",rename);

        editJson.getJSONObject("files")
                .put(whichFile,fileObject);
    }

    public void editFileContent(final String whichFile, final String content) {
        final JSONObject fileObject = new JSONObject();
        fileObject.put("content",content);

        editJson.getJSONObject("files")
                .put(whichFile,fileObject);
    }

    /*!
     * @brief use toString() method to get the json from your settings
     * @return JSON-encoded settings for your edited gist
     */
    @Override
    public String toString() {
        return editJson.toString();
    }
}
