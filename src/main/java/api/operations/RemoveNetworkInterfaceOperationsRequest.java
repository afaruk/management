package api.operations;

import api.OperationRequest;

public class RemoveNetworkInterfaceOperationsRequest implements OperationRequest<String> {
    @Override
    public String getType() {
        return "RemoveNetworkInterface";
    }

    @Override
    public String getParameters() {
        return "test id";
    }
}
