package clientApi.operations;

import clientApi.ActionRequest;

public class RemoveNetworkInterfaceOperationsRequest implements ActionRequest<String> {
    @Override
    public String getType() {
        return "RemoveNetworkInterface";
    }

    @Override
    public String getParameters() {
        return "test id";
    }
}
