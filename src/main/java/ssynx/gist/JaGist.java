package ssynx.gist;

import java.io.IOException;
import java.net.Authenticator;
import java.util.Calendar;
import java.util.TreeSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

public final class JaGist {

    public static void setAuthenticator(final JaGistAuthenticator auth) {
        Authenticator.setDefault(auth);
    }

    public static String dateToTimestamp(int y, int m, int d, int h, int mn, int s) {
        return String.format("%04d-%02d-%02dT%02d:%02d:%02dZ",y,m,d,h,mn,s);
    }
    
    /*
    public static String dateToTimestamp(Calendar d) {
        //todo
    }
    */

    public static class GetGist {
        public static Gist[] pub()
                throws JaGistException {
            final JSONArray gistsArray;
            final Set<Gist> gists = new TreeSet<>();
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
        public static Gist[] pub(final String timestamp) throws JaGistException {
            final JSONArray gistsArray;
            final Set<Gist> gists = new TreeSet<>();
            final String jsonStr;

            try {
                jsonStr = JaGistHttps.get("?since="+timestamp);
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

        public static Gist[] user(final String user) {
            return null;
        }

        public static Gist[] starred() {
            return null;
        }

        public static Gist single(final String id)
                throws JaGistException {
            final JSONObject obj;
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/"+id);
            } catch(IOException ioe) {
                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            if(jsonStr == null)
                return null;

            return new Gist(jsonStr);
        }

        public static Gist single(final String id, final String commitSha) {
            return null;
        }

        public static Gist[] singleCommits(final String id) {
            return null;
        }

        public static boolean isStarred(final String id) {
            return false;
        }

        public static Gist[] forks(final String id) {
            return null;
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
