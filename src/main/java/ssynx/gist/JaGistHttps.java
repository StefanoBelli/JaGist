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

    static String readFile(File f)
            throws IOException {
        StringBuilder content = new StringBuilder();

        FileInputStream ist = new FileInputStream(f);
        InputStreamReader reader = new InputStreamReader(ist,
                Charset.forName("UTF-8"));

        try(BufferedReader buffer = new BufferedReader(reader)){
            String line;
            while((line = buffer.readLine()) != null)
                content.append(line).append('\n');
        } finally {
            ist.close();
            reader.close();
        }

        return content.toString();
    }

    private static String getResponse(final InputStream stream)
            throws IOException {
        StringBuilder full = new StringBuilder();
        String line;

        InputStreamReader reader = new InputStreamReader(stream,Charset.forName("UTF-8"));
        try (BufferedReader streamBuf = new BufferedReader(reader)) {

            while ((line = streamBuf.readLine()) != null)
                full.append(line);

        } finally {
            reader.close();
            stream.close();
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
        final URL target = new URL("https://api.github.com/gists" + operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        if(basicAuth != null)
            connection.setRequestProperty("Authorization",basicAuth);

        OutputStream ost = connection.getOutputStream();
        try(final DataOutputStream requestBody = new DataOutputStream(ost)) {
            requestBody.writeBytes(what);
        } finally {
            ost.close();
        }

        String res;
        try {
            res = getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }

        return res;
    }

    static String patch(final String operation, final String what)
            throws IOException {
        final URL target = new URL("https://api.github.com/gists" + operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("X-HTTP-Method-Override", "PATCH");
        if (basicAuth != null)
            connection.setRequestProperty("Authorization", basicAuth);

        OutputStream ost = connection.getOutputStream();
        try(final DataOutputStream requestBody = new DataOutputStream(ost)) {
            requestBody.writeBytes(what);
        } finally {
            ost.close();
        }

        String res;
        try {
            res = getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }

        return res;
    }

    static void delete(final String operation)
            throws IOException {
        final URL target = new URL("https://api.github.com/gists" + operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        connection.setRequestMethod("DELETE");
        if (basicAuth != null)
            connection.setRequestProperty("Authorization", basicAuth);

        try {
            getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }
    }

    static void put(final String operation)
            throws IOException {
        final URL target = new URL("https://api.github.com/gists" + operation);
        final HttpsURLConnection connection = (HttpsURLConnection) target.openConnection();

        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Content-Length","0");
        if (basicAuth != null)
            connection.setRequestProperty("Authorization", basicAuth);

        try {
            getResponse(connection.getInputStream());
        } finally {
            assignLast(connection);
        }
    }
}
