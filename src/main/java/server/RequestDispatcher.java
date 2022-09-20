package server;

import api.OperationRequest;
import api.Response;

public interface RequestDispatcher {

    void registerHandler(RequestHandler hadler);

    Response dispatch(OperationRequest request);
}
