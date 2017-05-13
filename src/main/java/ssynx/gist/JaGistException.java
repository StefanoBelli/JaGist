package ssynx.gist;

public class JaGistException extends Exception {

    private final String strError;
    private final int codeError;

    public JaGistException(final String strError, final int codeError) {
        this.strError = strError;
        this.codeError = codeError;
    }

    public int getCodeError() {
        return codeError;
    }

    public String getStrError() {
        return strError;
    }

    @Override
    public String toString() {
        return String.format("HTTP error: %d, message: %s",codeError,strError);
    }
}
