package server.operations;

import api.Response;
import api.operations.RemoveNetworkInterfaceOperationsRequest;
import api.operations.RemoveNetworkInterfaceResponse;
import server.RequestHandler;

public class RemoveNetworkInterfaceOpRequestHandler implements RequestHandler<RemoveNetworkInterfaceOperationsRequest> {

    @Override
    public Response handle(RemoveNetworkInterfaceOperationsRequest request) {
        return new RemoveNetworkInterfaceResponse();
    }

    @Override
    public String getRequestType() {
        return "RemoveNetworkInterface";
    }
}
