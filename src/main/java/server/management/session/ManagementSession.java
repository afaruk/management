package server.management.session;

import server.user.autz.Role;

import java.util.List;

public class ManagementSession {

    private String userPrincipal;
    private List<Role> roles;

    public ManagementSession(String userPrincipal, List<Role> roles) {
        this.userPrincipal = userPrincipal;
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public String getUserPrincipal() {
        return userPrincipal;
    }
}
