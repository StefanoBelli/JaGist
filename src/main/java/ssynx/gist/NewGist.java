package ssynx.gist;

import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;

import org.json.JSONObject;

/*!
 * @brief Object representing new gist, refer to: JaGist.PerformGist.create() method
 */
public class NewGist {

    private final String desc;
    private final boolean isPublic;
    private Map<String,JSONObject> files = new LinkedHashMap<>();

    /*!
     * @brief constructs NewGist object without passing File object
     * @param description : Gist description
     * @param isPublic : Is the new gist public?
     * @param filename : first (necessary) file's name
     * @param content : first (necessary) file's content
     */
    public NewGist(final String description, final boolean isPublic,
                   final String filename, final String content) {
        desc = description;
        this.isPublic = isPublic;

        final JSONObject fileobj = new JSONObject();
        fileobj.put("content",content);

        files.put(filename, fileobj);
    }

    /*!
    * @brief constructs NewGist object passing File object
    * @param description : Gist description
    * @param isPublic : Is the new gist public?
    * @param file : File object
    */
    public NewGist(final String description, final boolean isPublic,
                   final File file)
            throws IOException {
        desc = description;
        this.isPublic = isPublic;

        final JSONObject fileobj = new JSONObject();
        fileobj.put("content",JaGistHttps.readFile(file));

        files.put(file.getName(),fileobj);
    }

    /*!
     * @brief add new file to the gist
     * @pararm filename : new filename
     * @param content : new file's content
     */
    public void addFile(final String filename, final String content) {
        final JSONObject fileobj = new JSONObject();
        fileobj.put("content",content);

        files.put(filename,fileobj);
    }

    /*!
     * @brief add new file to the gist
     * @param file : File object
     */
    public void addFile(final File file)
            throws IOException {
        final JSONObject fileobj = new JSONObject();
        fileobj.put("content",JaGistHttps.readFile(file));

        files.put(file.getName(),fileobj);
    }

   /*!
    * @brief use toString() method to get the json from your settings
    * @return JSON-encoded settings for your new Gist
    */
    @Override
    public String toString() {
        JSONObject gistobj = new JSONObject(),
                   fileobj = new JSONObject();

        gistobj.put("public",isPublic);
        gistobj.put("description",desc);

        for(final Map.Entry<String,JSONObject> entry : files.entrySet())
            fileobj.put(entry.getKey(),entry.getValue());

        gistobj.put("files",fileobj);

        return gistobj.toString();
    }
}
