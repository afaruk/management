package clientApi;

public class ManagementResponse implements Response {

    private ActionResponse response;
    private int requestId;

    public ManagementResponse(ActionResponse response, int requestId) {
        this.response = response;
        this.requestId = requestId;
    }

    @Override
    public ActionResponse getActionResponse() {
        return response;
    }

    @Override
    public int getRequestId() {
        return requestId;
    }

}
