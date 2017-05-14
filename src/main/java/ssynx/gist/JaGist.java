package ssynx.gist;

import java.io.IOException;
import java.net.Authenticator;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;

public class JaGist {

    public static void setAuthenticator(final JaGistAuthenticator auth) {
        Authenticator.setDefault(auth);
    }

    public static class GetGist {
        public static Gist[] pub() throws JaGistException {
            final JSONArray gistsArray;
            final Set<Gist> gists = new HashSet<>();
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

        public static Gist pub(final String timestamp) {
            return null;
        }

        public static Gist[] user(final String user) {
            return null;
        }

        public static Gist[] starred() {
            return null;
        }

        public static Gist single(final String id) {
            return null;
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