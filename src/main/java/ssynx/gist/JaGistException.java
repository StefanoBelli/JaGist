package ssynx.gist;

/*!
 * @brief Generic JaGist Exception class
 */
public class JaGistException extends Exception {

    private final String strError;
    private final int codeError;

    JaGistException(final String strError, final int codeError) {
        this.strError = strError;
        this.codeError = codeError;
    }

    /*!
     * @brief Use getCodeError() to see what's wrong
     * @return HTTP error code
     */
    public int getCodeError() {
        return codeError;
    }

    /*!
     * @brief Use getStrError() to see what's wrong
     * @return HTTP Status Text
     */
    public String getStrError() {
        return strError;
    }

    @Override
    public String toString() {
        return String.format("HTTP error: %d, message: %s",codeError,strError);
    }
}
