package server.operations;

import api.Response;
import api.operations.AddNetworkInterfaceOperationRequest;
import api.operations.AddNetworkInterfaceOperationResponse;
import server.RequestHandler;

import java.util.Map;

public class AddNetworkInterfaceOpRequestHandler implements RequestHandler<AddNetworkInterfaceOperationRequest> {

    @Override
    public Response handle(AddNetworkInterfaceOperationRequest request) {
        Map<String, String> parameters = request.getParameters();
        System.out.println(parameters);
        return new AddNetworkInterfaceOperationResponse("Başarılı");
    }

    @Override
    public String getRequestType() {
        return "AddNetworkInterface";
    }
}
