package server.management.session;

import server.user.autz.Role;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementSessionServiceImpl implements ManagementSessionService {

    private Map<String, ManagementSession> sessions = new HashMap<>();

    @Override
    public void createSession(String userPrincipal, List<Role> roles) {
        sessions.put(userPrincipal, new ManagementSession(userPrincipal, roles));
    }

    @Override
    public ManagementSession getSession(String userPrincipal) {
        return sessions.get(userPrincipal);
    }

}
