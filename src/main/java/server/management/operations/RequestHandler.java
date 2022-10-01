package server.management.operations;

import clientApi.ActionRequest;
import clientApi.ActionResponse;

public interface RequestHandler<T extends ActionRequest> {

    ActionResponse handle(T request);

    String getRequestType();
}
