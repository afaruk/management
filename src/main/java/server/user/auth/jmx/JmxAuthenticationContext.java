package server.user.auth.jmx;

import javax.security.auth.Subject;
import java.security.AccessControlContext;
import java.security.AccessController;

public class JmxAuthenticationContext {
    public static Subject getAuthenticatedUser() {
        AccessControlContext acc = AccessController.getContext();
        return Subject.getSubject(acc);
    }
}
