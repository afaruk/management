import clientApi.ManagementException;
import clientApi.ManagementRequest;
import clientApi.Request;
import clientApi.Response;
import clientApi.operations.AddNetworkInterfaceOperationRequest;
import clientApi.operations.AddNetworkInterfaceOperationResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import server.user.auth.ManagementAuthenticator;

import javax.management.MalformedObjectNameException;
import java.io.IOException;
import java.nio.file.Paths;

public class UnAuthorizedUserManagmentTest {

    private String configPath = Paths.get("src", "test","java", "config").toAbsolutePath().toString();

    @Test
    public void shouldTakeNot_AuthorizedException() throws IOException, MalformedObjectNameException, ManagementException {
        Client client = new Client("mehmet", "12345", configPath);

        try {
            var acRequest = new AddNetworkInterfaceOperationRequest.Builder().addNetworkAddress("1.1.1.1").addPort(333).build();
            Request opRequest = new ManagementRequest("mehmet", acRequest);
            client.sendRequest(opRequest);
        } catch (ManagementException e) {
            Assert.assertEquals(e.getType(), ManagementException.Type.NOT_AUTHORIZED);
            client.stopConnection();
            return;
        }

        Assert.fail();
    }

    @Test
    public void shouldTakeInvalidPrincipalException() throws IOException, MalformedObjectNameException {
        try {
            new Client("invalid name", "12345", configPath);
        } catch (SecurityException e) {
            Assert.assertEquals(e.getMessage(), "Invalid principal");
            return;
        }

        Assert.fail();
    }

    @Test
    public void shouldTakeInvalidPasswordException() throws IOException, MalformedObjectNameException {
        try {
            new Client("ahmet", "12345sss", configPath);
        } catch (SecurityException e) {
            Assert.assertEquals(e.getMessage(), "Invalid password");
            return;
        }

        Assert.fail();
    }
}
