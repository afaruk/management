package clientApi;

import java.util.Random;
import java.util.StringJoiner;

public class ManagementRequest implements Request {

    private final int id;
    private String userPrincipal;
    private ActionRequest request;

    public ManagementRequest(String userPrincipal, ActionRequest request) {
        this.userPrincipal = userPrincipal;
        this.request = request;
        this.id = new Random().nextInt();
    }
    @Override
    public String getUserPrincipal() {
        return userPrincipal;
    }

    @Override
    public ActionRequest getActionRequest() {
        return request;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        StringJoiner joiner = new StringJoiner("{", ",", "}");
        joiner.add("userPrincipal:" + userPrincipal);
        joiner.add("acRequest:" + request.toString());
        return joiner.toString();
    }
}
