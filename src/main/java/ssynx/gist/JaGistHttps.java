package ssynx.gist;

import com.sun.istack.internal.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.DataOutputStream;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

class JaGistHttps {
    private static int lastCode;
    private static String lastErrorMessage;

    @Nullable
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

    private static void assignLast(HttpsURLConnection conn) {
        try {
            lastCode = conn.getResponseCode();
            lastErrorMessage = conn.getResponseMessage();
        } catch(IOException ioe) {
            lastCode = -1;
            lastErrorMessage = "Unknown";
        }
    }

    public static int getLastCode() {
        return lastCode;
    }

    public static String getLastErrorMessage() {
        return lastErrorMessage;
    }

    @Nullable
    public static String get()
            throws IOException {
        final URL target = new URL("https://api.github.com/gists");
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        String res;
        try {
            res = getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }

        return res;
    }

    @Nullable
    public static String get(final String operation)
            throws IOException {
        final URL target = new URL("https://api.github.com/gists/"+operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        String res;
        try {
            res = getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }

        return res;
    }

    @Nullable
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

        String res;
        try {
            res = getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }

        return res;
    }

    //delete
    //put
    //patch
}
