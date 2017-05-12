package ssynx.gist;

import java.net.Authenticator;

public class JaGist {

    public static void setAuthenticator(final JaGistAuthenticator auth) {
        Authenticator.setDefault(auth);
    }

    public static class GetGist {
        public static Gist pub() {
            return null;
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