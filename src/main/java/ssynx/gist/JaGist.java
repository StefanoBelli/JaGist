package ssynx.gist;

import java.io.IOException;
import java.util.Calendar;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONObject;

public final class JaGist {

    public static void setCredentials(final String username, final String passwd) {
        JaGistHttps.setBasicAuth(username,passwd);
    }

    public static String dateToTimestamp(int year, int month, int day, int hour, int minute, int second) {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02dZ",
                year,
                month,
                day,
                hour,
                minute,
                second);
    }

    public static String dateToTimestamp(Calendar c) {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02dZ",
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH),
                c.get(Calendar.HOUR_OF_DAY),
                c.get(Calendar.MINUTE),
                c.get(Calendar.SECOND));
    }

    public static class GetGist {
        public static Gist[] pub()
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            final String jsonStr;

            try {
                jsonStr = JaGistHttps.get();
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }

        //FromDateTimestamp
        public static Gist[] pub(final String timestamp)
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            final String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists","?since="+timestamp);
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
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
            final String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/users/"+user+"/gists","");
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
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
            final String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/starred","");
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }

        public static Gist single(final String id)
                throws JaGistException {
            final JSONObject obj;
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id,"");
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            if(jsonStr == null)
                return null;

            return new Gist(jsonStr);
        }

        public static Gist single(final String id, final String commitSha)
                throws JaGistException {
            final JSONObject obj;
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/"+commitSha,"");
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            if(jsonStr == null)
                return null;

            return new Gist(jsonStr);
        }

        public static GistCommit[] singleCommits(final String id)
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<GistCommit> gists = new Vector<>();
            final String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/commits","");
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new GistCommit(gistsArray.get(i).toString()));
            }

            return gists.toArray(new GistCommit[gists.size()]);
        }

        public static boolean isStarred(final String id) {
            try {
                JaGistHttps.get("/gists/" + id + "/star", "");
            } catch(IOException ioe) {
                return false;
            }

            return true;
        }

        public static Gist[] forks(final String id)
                throws JaGistException {
            final JSONArray gistsArray;
            final Vector<Gist> gists = new Vector<>();
            final String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/forks","");
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            if(jsonStr != null) {
                gistsArray = new JSONArray(jsonStr);
                for (int i = 0; i < gistsArray.length(); i++)
                    gists.add(new Gist(gistsArray.get(i).toString()));
            }

            return gists.toArray(new Gist[gists.size()]);
        }
    }

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
