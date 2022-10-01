package server.management.session;

import server.user.autz.Role;

import java.util.List;

public interface ManagementSessionService {

    void createSession(String userPrincipal, List<Role> roles);
    ManagementSession getSession(String userPrincipal);
}
