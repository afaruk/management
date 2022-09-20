package server;

import java.util.List;

public class ManagementSession {

    private String userPrincipal;
    private List<String> userPrivileges;

    public ManagementSession(String userPrincipal, List<String> userPrivileges) {
        this.userPrincipal = userPrincipal;
        this.userPrivileges = userPrivileges;
    }

    public boolean checkPrivilege(List<String> privileges) {
        return privileges.stream()
                .map(pr -> userPrivileges.contains(pr))
                .filter(k -> k == true).findFirst().orElse(false);
    }
}
