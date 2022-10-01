package server.management;

import clientApi.*;
import server.management.operations.RequestDispatcher;
import server.management.session.ManagementSessionService;
import server.user.autz.Privilege;
import server.user.autz.Role;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ManagementServiceImpl implements ManagementService {

    private RequestDispatcher dispatcher;
    private ManagementSessionService sessionService;

    public ManagementServiceImpl(ManagementSessionService sessionService, RequestDispatcher dispatcher) {
        this.sessionService = sessionService;
        this.dispatcher = dispatcher;
    }

    @Override
    public Response execute(Request opRequest) throws ManagementException {
        if (!isAuthorized(opRequest)) {
            throw new ManagementException(ManagementException.Type.NOT_AUTHORIZED);
        }
        ActionResponse acResponse = dispatcher.handle(opRequest.getActionRequest());
        return new ManagementResponse(acResponse, opRequest.getId());
    }

    private boolean isAuthorized(Request request) {
        var session = sessionService.getSession(request.getUserPrincipal());
        if (session == null) {
            return false;
        }

        return checkPrivilege(session.getRoles(), request.getActionRequest().getType());
    }

    private boolean checkPrivilege(List<Role> roles, String action) {
        return roles.stream()
                .flatMap(s -> s.getPrivileges().stream())
                .collect(Collectors.toList())
                    .stream()
                    .map(privilege -> privilege.getActions().contains(action))
                    .filter(k -> k == true)
                    .findFirst().orElse(false);
    }
}
