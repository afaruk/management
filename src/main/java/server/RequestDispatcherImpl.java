package server;

import api.OperationRequest;
import api.Response;

import java.util.HashMap;
import java.util.Map;

public class RequestDispatcherImpl implements RequestDispatcher {

    private Map<String, RequestHandler> handlers = new HashMap<>();

    @Override
    public void registerHandler(RequestHandler handler) {
        handlers.put(handler.getRequestType(), handler);
    }

    @Override
    public Response dispatch(OperationRequest request) {
        return handlers.get(request.getType()).handle(request);
    }
}
