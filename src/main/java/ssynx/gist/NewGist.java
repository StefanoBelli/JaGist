package ssynx.gist;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.nio.charset.Charset;

import org.json.JSONObject;

public class NewGist {

    private final String desc;
    private final boolean isPublic;
    private Map<String,String> files = new HashMap<>();

    private String readFile(File f) throws IOException {
        StringBuilder content = new StringBuilder();

        InputStreamReader reader;
        reader = new InputStreamReader(new FileInputStream(f),
                        Charset.forName("UTF-8"));

        char[] bytes = new char[1024];

        try {
            while (reader.read(bytes) != -1)
                content.append(new String(bytes));
        } finally {
            reader.close();
        }

        return content.toString();
    }

    public NewGist(final String description, final boolean isPublic,
                   final String filename, final String content) {
        desc = description;
        this.isPublic = isPublic;
        files.put(filename, content);
    }

    public NewGist(final String description, final boolean isPublic,
                   final File file)
            throws IOException {
        desc = description;
        this.isPublic = isPublic;
        files.put(file.getName(),readFile(file));
    }

    public void addFile(final String filename, final String content) {
        files.put(filename,content);
    }

    public void addFile(final File file) throws IOException {
        files.put(file.getName(),readFile(file));
    }

    @Override
    public String toString() {
        JSONObject gistobj = new JSONObject(),
                   fileobj = new JSONObject();

        gistobj.put("public",isPublic);
        gistobj.put("description",desc);

        for(Map.Entry<String,String> entry : files.entrySet())
            fileobj.put(entry.getKey(),entry.getValue());

        gistobj.put("files",fileobj);

        return gistobj.toString();
    }
}
