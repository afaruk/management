package api;

public class ManagementRequest implements Request {

    private String userPrincipal;
    private OperationRequest request;

    public ManagementRequest(String userPrincipal, OperationRequest request) {
        this.userPrincipal = userPrincipal;
        this.request = request;
    }

    @Override
    public String getUserPrincipal() {
        return userPrincipal;
    }

    @Override
    public OperationRequest getOperationRequest() {
        return request;
    }
}
