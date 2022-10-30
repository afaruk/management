package server.user.auth;

import com.sun.security.auth.UserPrincipal;
import server.user.User;
import server.user.UserRepository;
import server.user.auth.jmx.AuthenticatedUserPrincipal;

import javax.management.remote.JMXAuthenticator;
import javax.security.auth.Subject;

public class ManagementAuthenticator implements JMXAuthenticator {
    private UserRepository userRepository;

    public ManagementAuthenticator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Subject authenticate(Object credentials) {
        String[] strs = (String[]) credentials;
        String principal = strs[0];
        User user = userRepository.getUser(principal);
        if (user == null) {
            throw new SecurityException("Invalid principal");
        }

        String password = strs[1];
        if (!user.getPassword().equals(password)) {
            throw new SecurityException("Invalid password");
        }

        AuthenticatedUser authenticatedUser = new AuthenticatedUser(principal, user.getRoles());
        Subject subject = new Subject();
        subject.getPrincipals().add(new AuthenticatedUserPrincipal(authenticatedUser));
        return subject;
    }
}
