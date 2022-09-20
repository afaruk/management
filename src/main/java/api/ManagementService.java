package api;

public interface ManagementService {

    Response execute(Request request) throws ManagementException;
}
