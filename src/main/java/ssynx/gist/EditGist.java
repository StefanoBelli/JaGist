package ssynx.gist;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

/*!
 * @brief object used to edit a gist, refer to: JaGist.PerformGist.edit() method
 *        NOTE: EACH FILE CONTENT PROPERTY MUST NOT BE EMPTY!
 */
public class EditGist {

    private final JSONObject editJson;

    /*!
     * @brief construct object specifying new description
     * @param description : new description
     */
    public EditGist(final String description) {
        final JSONObject files = new JSONObject();
        editJson = new JSONObject();
        editJson.put("files",files);
        editJson.put("description",description);
    }

    /*!
     * @brief construct object leaving description unchanged
     */
    public EditGist() {
        final JSONObject files = new JSONObject();
        editJson = new JSONObject();
        editJson.put("files",files);
    }

    /*!
     * @param whichFile : filename to delete
     */
    public void deleteFile(final String whichFile) {
        editJson.getJSONObject("files")
                .put(whichFile,JSONObject.NULL);
    }

    /*!
     * @param whichFile : old filename
     * @param rename : new filename
     * @param content : new content
     */
    public void editFile(final String whichFile, final String rename,
                         final String content) {
        final JSONObject fileObject = new JSONObject();
        fileObject.put("content",content);
        fileObject.put("filename",rename);

        editJson.getJSONObject("files")
                .put(whichFile,fileObject);
    }

    /*!
     * @brief consider using editFile() instead of editFileName()
     * @param whichFile : old filename
     * @param rename : new filename
     */
    public void editFileName(final String whichFile, final String rename) {
        final JSONObject obj = editJson.getJSONObject("files");
        if(!obj.has(whichFile)) {
            final JSONObject fileObject = new JSONObject();
            fileObject.put("filename", rename);
            obj.put(whichFile, fileObject);
        } else
            obj.getJSONObject(whichFile)
                    .put("filename", rename);
    }

    /*!
     * @brief consider using editFile() instead of editFileContent()
     * @param whichFile : [old?] filename
     * @param content : new content
     */
    public void editFileContent(final String whichFile, final String content) {
        final JSONObject obj = editJson.getJSONObject("files");
        if(!obj.has(whichFile)) {
            final JSONObject fileObject = new JSONObject();
            fileObject.put("content", content);
            obj.put(whichFile, fileObject);
        } else
            obj.getJSONObject(whichFile)
                    .put("content",content);
    }

    /*!
     * @param whichFile : old filename
     * @param f : File instance of new file (content/filename)
     */
    public void editFile(final String whichFile, final File f)
            throws IOException {
        final JSONObject fileObject = new JSONObject();
        fileObject.put("content",JaGistHttps.readFile(f));
        fileObject.put("filename",f.getName());

        editJson.getJSONObject("files")
                .put(whichFile,fileObject);
    }

    /*!
     * @brief consider using editFile() instead of editFileName()
     * @param whichFile : old filename
     * @param f : File object of new file
     */
    public void editFileName(final String whichFile, final File f) {
        final JSONObject obj = editJson.getJSONObject("files");
        if(!obj.has(whichFile)) {
            final JSONObject fileObject = new JSONObject();
            fileObject.put("filename", f.getName());
            obj.put(whichFile, fileObject);
        } else
            obj.getJSONObject(whichFile)
                    .put("filename",f.getName());
    }

    /*!
     * @brief consider using editFile() instead of editFileContent()
     * @param whichFile : [old?] filename
     * @param f : File object of new file
     */
    public void editFileContent(final String whichFile, final File f)
            throws IOException {
        final JSONObject obj = editJson.getJSONObject("files");
        if(!obj.has(whichFile)) {
            final JSONObject fileObject = new JSONObject();
            fileObject.put("content", JaGistHttps.readFile(f));
            obj.put(whichFile, fileObject);
        } else
            obj.getJSONObject(whichFile)
                    .put("content",JaGistHttps.readFile(f));
    }

    /*!
     * @param f : File object
     */
    public void deleteFile(final File f) {
        editJson.getJSONObject("files")
                .put(f.getName(),JSONObject.NULL);
    }

    /*!
     * @param f : File object
     */
    public void addFile(final File f)
            throws IOException {
        final JSONObject fileObject = new JSONObject();
        fileObject.put("content",JaGistHttps.readFile(f));

        editJson.getJSONObject("files")
                .put(f.getName(),fileObject);
    }

    /*!
     * @param name : new name for the new file
     * @param content : content of the new file
     */
    public void addFile(final String name, final String content) {
        final JSONObject fileObject = new JSONObject();
        fileObject.put("content",content);

        editJson.getJSONObject("files")
                .put(name,fileObject);
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
