package api.operations;

import api.Response;

public class AddNetworkInterfaceOperationResponse implements Response {

    private String result;

    public AddNetworkInterfaceOperationResponse(String result) {

        this.result = result;
    }

    public String getResult() {
        return result;
    }
}
