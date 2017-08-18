package ssynx.gist;

import java.io.IOException;
import java.util.Calendar;

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
     * @brief This method let you get the last HTTP error code by request
     * @return HTTP integer error code
     */
    public static int getRequestCode() {
        return JaGistHttps.getLastCode();
    }

    /*!
     * @brief This method let you get the last HTTP error string by request
     * @return HTTP message string
     */
    public static String getRequestString() {
        return JaGistHttps.getLastErrorMessage();
    }

    /*!
     * @brief Inner static class which contains Gist-information fetching methods
     */
    public static class GetGist {

        /*!
         * @brief get me EEVERYTHINGGGG
         * @return public gists
         */
        public static Gist[] pub()
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get();
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return new Gist[0];

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            final JSONArray gistsArray = new JSONArray(jsonStr);
            int arrLen = gistsArray.length();
            Gist[] gists = new Gist[arrLen];

            for (int i = 0; i < arrLen; i++)
                gists[i] = new Gist(gistsArray.get(i).toString());

            return gists;
        }

        /*!
         * @brief check JaGist.dateToTimestamp() method
         * @param timestamp : ISO-8601 timetamp
         * @return Gists from that date to now
         */
        public static Gist[] pub(final String timestamp)
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists","?since="+timestamp);
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return new Gist[0];

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            final JSONArray gistsArray = new JSONArray(jsonStr);
            int arrLen = gistsArray.length();
            Gist[] gists = new Gist[arrLen];

            for (int i = 0; i < gistsArray.length(); i++)
                gists[i] = new Gist(gistsArray.get(i).toString());

            return gists;
        }

        /*!
         * @brief get every gist from 'user'
         * @param user : username
         * @return user's gists
         */
        public static Gist[] user(final String user)
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/users/"+user+"/gists","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return new Gist[0];

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            final JSONArray gistsArray = new JSONArray(jsonStr);
            int arrLen = gistsArray.length();
            Gist[] gists = new Gist[arrLen];

            for (int i = 0; i < gistsArray.length(); i++)
                gists[i] = new Gist(gistsArray.get(i).toString());

            return gists;
        }

        /*!
         * @brief get YOUR starred gists (check setCredentials() method)
         * [REQUIRES AUTHENTICATION]
         * @return your starred gists
         */
        public static Gist[] starred()
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/starred","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return new Gist[0];

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            final JSONArray gistsArray = new JSONArray(jsonStr);
            int arrLen = gistsArray.length();
            Gist[] gists = new Gist[arrLen];

            for (int i = 0; i < gistsArray.length(); i++)
                gists[i] = new Gist(gistsArray.get(i).toString());

            return gists;
        }

        /*!
         * @brief retrieves single gist by its id
         * @param id : gist's id
         * @return requested gist ( WARNING: MAY BE NULL! )
         */
        @Nullable
        public static Gist single(final String id)
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id,"");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            return new Gist(jsonStr);
        }

        /*!
         * @brief retrieves single gist by its id, with specific revision
         * @param id : gist's id
         * @param commitSha : gist specific revision commit SHA
         * @return requested gist by commit ( WARNING: MAY BE NULL! )
         */
        @Nullable
        public static Gist single(final String id, final String commitSha)
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/"+commitSha,"");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            return new Gist(jsonStr);
        }

        /*!
         * @brief Retrieves all commits for Gist's ID
         * @param id : gist ID
         * @return all of its commits
         */
        public static GistHistory[] singleCommits(final String id)
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/commits","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return new GistHistory[0];

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            final JSONArray gistsArray = new JSONArray(jsonStr);
            int arrLen = gistsArray.length();
            GistHistory[] historyArr = new GistHistory[arrLen];

            for (int i = 0; i < arrLen; i++)
                historyArr[i] = new GistHistory(
                        new JSONObject(gistsArray.get(i).toString()));

            return historyArr;
        }

        /*!
         * @brief checks if you starred a gist by its id (check setCredentials() method)
         * [REQUIRES AUTHENTICATION]
         * @return did you starred that gist?
         */
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

        /*!
         * @brief specific-gist forks
         * @param id : gist's id
         * @return Gist forks
         */
        public static Gist[] forks(final String id)
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/"+id+"/forks","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return new Gist[0];

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                            code);
            }

            final JSONArray gistsArray = new JSONArray(jsonStr);
            int arrLen = gistsArray.length();
            Gist[] gists = new Gist[arrLen];

            for (int i = 0; i < gistsArray.length(); i++)
                gists[i] = new Gist(gistsArray.get(i).toString());

            return gists;
        }
    }

    /*!
     * @brief Inner static class which contains Gists information changing methods
     */
    public static class PerformGist {

        /*!
         * @brief Create new Gist
         * @param gist : your new gist (refer to NewGist class)
         * @return new Gist by response (may return null)
         */
        @Nullable
        public static Gist create(final NewGist gist)
                throws JaGistException {
            String newGist;
            try {
                newGist = JaGistHttps.post("",gist.toString());
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            return new Gist(newGist);
        }

        /*!
         * @brief Edit a gist
         * @param gist : your edited gist (refer to EditGist class)
         * @param id : your target gist ID
         * @return new edited gist by response (may return null)
         */
        @Nullable
        public static Gist edit(final EditGist gist,final String id)
                throws JaGistException {
            String newGist;
            try {
                newGist = JaGistHttps.patch("/"+id,gist.toString());
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        JaGistHttps.getLastCode());
            }

            return new Gist(newGist);
        }

        /*!
         * @brief Star a gist (requires authentication, see JaGist's setCredentials() method)
         * @param id: your gist's ID
         * @return true if correctly starred, false otherwise
         */
        public static boolean star(final String id)
                throws JaGistException {
            try {
                JaGistHttps.put("/"+id+"/star");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return false;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        code);
            }

            return true;
        }

        /*!
         * @brief Unstar a gist (requires authentication, see JaGist's setCredentials() method)
         * @param id: your gist's ID
         * @return true if correctly unstarred, false otherwise
         */
        public static boolean unstar(final String id)
                throws JaGistException {
            try {
                JaGistHttps.delete("/"+id+"/star");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return false;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        code);
            }

            return true;
        }

        /*!
         * @brief fork a gist (requires authentication)
         * @param id : your gist id
         * @return new Gist by response (may return null)
         */
        @Nullable
        public static Gist fork(final String id)
                throws JaGistException {
            String strFork;

            try {
                strFork = JaGistHttps.post("/"+id+"/forks","");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        code);
            }

            return new Gist(strFork);
        }

        /*!
         * @brief Delete a gist (requires authentication, see JaGist's setCredentials() method)
         * @param id: your gist's ID
         * @return true if correctly deleted, false otherwise
         */
        public static boolean delete(final String id)
                throws JaGistException {
            try {
                JaGistHttps.delete("/"+id);
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return false;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(),
                        code);
            }

            return true;
        }
    }

    /*!
     * @brief Inner static class which contains Gists commenting methods
     */
    public static class CommentGist {

        /*!
         * @brief List comments on a specific gist
         * @param id : your gist's ID
         * @return GistComment array, representing comments
         */
        public static GistComment[] list(final String id)
                throws JaGistException {
            String jsonStr;

            try {
                jsonStr = JaGistHttps.get("/gists/",id+"/comments");
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return new GistComment[0];

                throw new JaGistException(JaGistHttps.getLastErrorMessage(), code);
            }

            final JSONArray gistsCommentArray = new JSONArray(jsonStr);
            int arrLen = gistsCommentArray.length();
            GistComment[] gistComments = new GistComment[arrLen];

            for (int i = 0; i < arrLen; i++)
                gistComments[i] = new GistComment(
                        gistsCommentArray.getJSONObject(i));

            return gistComments;
        }

        /*!
         * @brief Get a specific comment on gist
         * @param id: your gist's ID
         * @param commentId : comment ID
         * @return Requested comment (may return null)
         */
        @Nullable
        public static GistComment get(final String id, final int commentId)
                throws JaGistException {
            String fullJson;

            try {
                fullJson = JaGistHttps.get("/gists/",id+"/comments/"+commentId);
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(), code);
            }

            return new GistComment(new JSONObject(fullJson));
        }

        /*!
         * @brief Create new comment on gist
         * @param id: your gist ID
         * @param body: your new comment's content
         * @return Your new posted comment on gist (may return null)
         */
        @Nullable
        public static GistComment create(final String id, final String body)
                throws JaGistException {
            final String encodedBody = "{ \"body\": \""+body+"\" }";
            String fullJson;

            try {
                fullJson = JaGistHttps.post("/"+id+"/comments",encodedBody);
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(), code);
            }


            return new GistComment(new JSONObject(fullJson));
        }

        /*!
         * @brief Edit a comment on a gist
         * @param id: your gist's ID
         * @param commentId: your target comment on a Gist
         * @param body: new edited content
         * @return Your edited comment (may return null)
         */
        @Nullable
        public static GistComment edit(final String id, final int commentId, final String body)
                    throws JaGistException {
            final String encodedBody = "{ \"body\": \""+body+"\" }";
            String fullJson;

            try {
                fullJson = JaGistHttps.patch("/"+id+"/comments/"+commentId,encodedBody);
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return null;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(), code);
            }

            return new GistComment(new JSONObject(fullJson));
        }

        /*!
         * @brief Delete a comment
         * @param id : your gist's ID
         * @param commentId : comment ID
         * @return true if correctly removed, false otherwise
         */
        public static boolean delete(final String id, final int commentId)
                throws JaGistException {
            try {
                JaGistHttps.delete("/"+id+"/comments/"+commentId);
            } catch(IOException ioe) {
                int code = JaGistHttps.getLastCode();
                if(code == 404)
                    return false;

                throw new JaGistException(JaGistHttps.getLastErrorMessage(), JaGistHttps.getLastCode());
            }

            return true;
        }
    }
}
