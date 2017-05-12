package ssynx.gist;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class JaGistAuthenticator extends Authenticator {

    private final String usern, passwd;

    public JaGistAuthenticator(final String username,
                               final String password) {
        usern = username;
        passwd = password;
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(usern,passwd.toCharArray());
    }
}
