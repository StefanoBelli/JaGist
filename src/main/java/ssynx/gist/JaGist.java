package ssynx.gist;

import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.annotation.Nullable;

/*!
 * @brief Main JaGist class
 */
public final class JaGist {

    /*!
     * @brief HTTP requests will contain basic authentication headers
     * @param username : username
     * @param passwd   : password
     */
    public static void setCredentials(final String username, final String passwd) {
        JaGistHttps.setBasicAuth(username,passwd);
    }

    /*!
     * @brief This method overload returns a timestamp in ISO 8601 by each date parameter
     * @param year
     * @param month
     * @param day
     * @param hour
     * @param minute
     * @param second
     * @return A timestamp in ISO 8601 format
     */
    public static String dateToTimestamp(int year, int month, int day, int hour, int minute, int second) {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02dZ",
                year,
                month,
                day,
                hour,
                minute,
                second);
    }

    /*!
     * @brief This method overload returns a timestamp in ISO 8601 by a Calendar object
     * @param c : Calendar object
     * @return A timestamp in ISO 8601 format
     */
    public static String dateToTimestamp(Calendar c) {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02dZ",
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND));
    }

    /*!
     * @brief Inner static class which contains Gist-information fetching methods
     */
    public static class GetGist {

        public static Gist[] pub()
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get();
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }

        /*!
         * @brief check JaGist.dateToTimestamp() method
         */
        public static Gist[] pub(final String timestamp)
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get("/gists","?since="+timestamp);
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }

        public static Gist[] user(final String user)
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get("/users/"+user+"/gists","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }

        public static Gist[] starred()
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get("/gists/starred","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }

        @Nullable
        public static Gist single(final String id)
                throws JaGistException {
            final JSONObject obj;
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id,"");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr == null)
                return null;

            return new Gist(jsonStr);
        }

        @Nullable
        public static Gist single(final String id, final String commitSha)
                throws JaGistException {
            final JSONObject obj;
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/"+commitSha,"");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr == null)
                return null;

            return new Gist(jsonStr);
        }

        public static GistHistory[] singleCommits(final String id)
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<GistHistory> gists = new Vector<>();
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/commits","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new GistHistory(new JSONObject(gistsArray.get(i).toString())));
            }

            return gists.toArray(new GistHistory[gists.size()]);
        }

        public static boolean isStarred(final String id)
                throws JaGistException {
            try {
                JaGistHttps.get("/gists/" + id + "/star", "");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return false;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        code);
            }

            return true;
        }

        public static Gist[] forks(final String id)
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            String jsonStr = null;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/forks","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code != 404)
                    throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }
    }

    /*!
     * @brief Inner static class which contains Gists information changing methods
     */
    public static class PerformGist {
        public static Gist create(final NewGist gist) {
            return null;
        }

        public static Gist edit(final EditGist gist) {
            return null;
        }

        public static boolean star(final String id) {
            return false;
        }

        public static boolean unstar(final String id) {
            return false;
        }

        public static Gist fork(final String id) {
            return null;
        }

        public static boolean delete(final String id) {
            return false;
        }
    }

}
