package ssynx.gist;

import java.net.Authenticator;

public class JaGist {

    public JaGist() {}
    public JaGist(final JaGistAuthenticator auth) {
        Authenticator.setDefault(auth);
    }
}