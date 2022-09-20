package server;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagementSessionServiceImpl implements ManagementSessionService {

    private Map<String, ManagementSession> sessions = new HashMap<>();

    @Override
    public void createSession(String userPrincipal, List<String> privileges) {
        sessions.put(userPrincipal, new ManagementSession(userPrincipal, privileges));
    }

    @Override
    public ManagementSession getSession(String userPrincipal) {
        return sessions.get(userPrincipal);
    }

}
