package server;

import java.util.List;

public interface ManagementSessionService {

    void createSession(String userPrincipal, List<String> privileges);
    ManagementSession getSession(String userPrincipal);
}
