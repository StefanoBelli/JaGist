package ssynx.gist;

import java.io.*;
import java.nio.charset.Charset;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.util.Base64;

class JaGistHttps {
    private static String basicAuth = null;
    private static int lastCode = 0;
    private static String lastErrorMessage = "";

    private static String getResponse(final InputStream stream) {
        StringBuilder full = new StringBuilder();
        String line;

        BufferedReader streamBuf = new BufferedReader(
                new InputStreamReader(stream,Charset.forName("UTF-8")));

        try {
            while ((line = streamBuf.readLine()) != null)
                full.append(line);
            streamBuf.close();
        } catch(IOException ioe) {
            return null;
        }

        return full.toString();
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

    static void setBasicAuth(String username, String password) {
        final byte[] all = (username+":"+password).getBytes();
        basicAuth = "Basic "+Base64.getEncoder().encodeToString(all);
    }

    static int getLastCode() {
        return lastCode;
    }

    static String getLastErrorMessage() {
        return lastErrorMessage;
    }

    static String get()
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

    static String get(final String getwhat, final String operation)
            throws IOException {
        final URL target = new URL("https://api.github.com"+getwhat+operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();
        if(basicAuth != null)
            connection.setRequestProperty("Authorization",basicAuth);

        String res;
        try {
            res = getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }

        return res;
    }

    static String post(final String operation, final String what)
            throws IOException {
        final URL target = new URL("https://api.github.com/gists/" + operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        if(basicAuth != null)
            connection.setRequestProperty("Authorization",basicAuth);

        final DataOutputStream requestBody = new DataOutputStream(connection.getOutputStream());
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
