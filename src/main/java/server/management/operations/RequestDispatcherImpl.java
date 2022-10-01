package server.management.operations;

import clientApi.ActionRequest;
import clientApi.ActionResponse;

import java.util.HashMap;
import java.util.Map;

public class RequestDispatcherImpl implements RequestDispatcher {

    private Map<String, RequestHandler> handlers = new HashMap<>();

    @Override
    public void registerHandler(RequestHandler handler) {
        handlers.put(handler.getRequestType(), handler);
    }

    @Override
    public ActionResponse handle(ActionRequest request) {
        return handlers.get(request.getType()).handle(request);
    }
}
