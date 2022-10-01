package server.management;

import clientApi.*;

public interface ManagementService {

    Response execute(Request request) throws ManagementException;
}
