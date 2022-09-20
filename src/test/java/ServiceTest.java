import api.ManagementException;
import api.ManagementRequest;
import api.Response;
import api.operations.AddNetworkInterfaceOperationRequest;
import api.operations.AddNetworkInterfaceOperationResponse;
import api.operations.RemoveNetworkInterfaceOperationsRequest;
import org.junit.Before;
import org.junit.Test;
import server.ManagementServiceImpl;
import server.ManagementSessionService;
import server.ManagementSessionServiceImpl;
import server.RequestDispatcherImpl;
import server.operations.AddNetworkInterfaceOpRequestHandler;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ServiceTest {

    ManagementServiceImpl service;
    private ManagementSessionServiceImpl sessionService;

    @Before
    public void setUp() {
        sessionService = new ManagementSessionServiceImpl();
        sessionService.createSession("ahmet", Arrays.asList("AddNetworkInterface"));
        sessionService.createSession("ali", Arrays.asList("RemoveNetworkInterface"));
        var requestDispatcher = new RequestDispatcherImpl();
        var addNetworkInterfaceOpRequestHandler = new AddNetworkInterfaceOpRequestHandler();
        requestDispatcher.registerHandler(addNetworkInterfaceOpRequestHandler);
        service = new ManagementServiceImpl(sessionService, requestDispatcher);
    }

    @Test
    public void shouldSuccessful() throws ManagementException {
        var opRequest = new AddNetworkInterfaceOperationRequest.Builder().addNetworkAddress("1.1.1.1").addPort(333).build();
        var response = (AddNetworkInterfaceOperationResponse) service.execute(new ManagementRequest("ahmet", opRequest));
        assertEquals(response.getResult(), "Başarılı");
    }

    @Test (expected = ManagementException.class)
    public void shouldTakeExceptionWhenUserNotHavePrivillage() throws ManagementException {
        var opRequest = new RemoveNetworkInterfaceOperationsRequest();
        service.execute(new ManagementRequest("ahmet", opRequest));
    }

    @Test
    public void shouldTakeExceptionWhenSessionIsNull() {
        var opRequest = new AddNetworkInterfaceOperationRequest.Builder().addNetworkAddress("1.1.1.1").addPort(333).build();
        try {
            service.execute(new ManagementRequest("mehmet", opRequest));
        } catch (ManagementException e) {
            assertEquals(e.getType(), ManagementException.Type.NOT_AUTHORIZED);
            return;
        }
        fail();
    }

}
