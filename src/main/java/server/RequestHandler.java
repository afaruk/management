package server;

import api.OperationRequest;
import api.Response;

public interface RequestHandler<T extends OperationRequest> {

    Response handle(T request);

    String getRequestType();
}
