package ssynx.gist;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

class JaGistHttps {

    private static String getResponse(final InputStream stream) {
        String full="", line;

        BufferedReader streamBuf = new BufferedReader(
                new InputStreamReader(stream));

        try {
            while ((line = streamBuf.readLine()) != null)
                full += line + '\n';
            streamBuf.close();
        } catch(IOException ioe) {
            return null;
        }

        return full;
    }

    public static String get()
            throws IOException {
        final URL target = new URL("https://api.github.com/gists");
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        return getResponse(connection.getInputStream());
    }

    public static String get(final String operation)
            throws IOException {
        final URL target = new URL("https://api.github.com/gists/"+operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        return getResponse(connection.getInputStream());
    }

    public static String post(final String operation, final String what)
            throws IOException {
        final URL target = new URL("https://api.github.com/gists/" + operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");

        DataOutputStream requestBody = new DataOutputStream(connection.getOutputStream());
        requestBody.writeBytes(what);
        requestBody.close();

        return getResponse(connection.getInputStream());
    }
}
