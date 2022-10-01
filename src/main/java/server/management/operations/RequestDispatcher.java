package server.management.operations;

import clientApi.ActionRequest;
import clientApi.ActionResponse;

public interface RequestDispatcher {

    void registerHandler(RequestHandler hadler);

    ActionResponse handle(ActionRequest request);
}
