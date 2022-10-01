package clientApi.mbean;


import clientApi.*;

public interface ManagementOperationsMBean {

    Response send(Request request) throws ManagementException;

}
