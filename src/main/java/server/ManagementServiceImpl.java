package server;

import api.ManagementException;
import api.ManagementService;
import api.Request;
import api.Response;

import java.util.Arrays;
import java.util.List;

public class ManagementServiceImpl implements ManagementService {

    private RequestDispatcher dispatcher;
    private ManagementSessionService sessionService;

    public ManagementServiceImpl(ManagementSessionService sessionService, RequestDispatcher dispatcher) {
        this.sessionService = sessionService;
        this.dispatcher = dispatcher;
    }

    @Override
    public Response execute(Request request) throws ManagementException {
        if (!isAuthorized(request)) {
            throw new ManagementException(ManagementException.Type.NOT_AUTHORIZED);
        }
        return dispatcher.dispatch(request.getOperationRequest());
    }

    private boolean isAuthorized(Request request) {
        var session = sessionService.getSession(request.getUserPrincipal());
        if (session == null) {
            return false;
        }

        List<String> privileges = determinePrivilege(request.getOperationRequest().getType());

        return session.checkPrivilege(privileges);
    }

    private List<String> determinePrivilege(String type) {
        return Arrays.asList(type);
    }
}
