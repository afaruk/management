package server.user.auth.jmx;

import server.user.auth.AuthenticatedUser;

import java.security.Principal;

public class AuthenticatedUserPrincipal implements Principal {

    private final AuthenticatedUser authenticatedUser;

    public AuthenticatedUserPrincipal(AuthenticatedUser authenticatedUser) {
        this.authenticatedUser = authenticatedUser;
    }

    @Override
    public String getName() {
        return authenticatedUser.getUserPrincipal();
    }

    public AuthenticatedUser getAuthenticatedUser() {
        return authenticatedUser;
    }
}
