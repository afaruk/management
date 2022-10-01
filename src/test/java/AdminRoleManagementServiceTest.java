import clientApi.ManagementException;
import clientApi.ManagementRequest;
import clientApi.Request;
import clientApi.Response;
import clientApi.operations.AddNetworkInterfaceOperationRequest;
import clientApi.operations.AddNetworkInterfaceOperationResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.management.MalformedObjectNameException;
import java.io.IOException;

public class AdminRoleManagementServiceTest {

    // NOT testlerden once Server çalıştırılmalıdır.

    private Client client;
    private String userPrincipal = "ahmet";

    @Before
    public void setUp() throws IOException, MalformedObjectNameException {
        String configPath = "D:\\GitRepo\\managment\\src\\test\\java\\config\\";
        client = new Client(userPrincipal, "12345", configPath);
    }

    @Test
    public void shouldAddNI() throws IOException, ManagementException {
        var acRequest = new AddNetworkInterfaceOperationRequest.Builder().addNetworkAddress("1.1.1.1").addPort(333).build();
        Request opRequest = new ManagementRequest(userPrincipal, acRequest);
        Response response = client.sendRequest(opRequest);
        System.out.println("İstek ID..." + response.getRequestId());
        AddNetworkInterfaceOperationResponse addNIResponse = (AddNetworkInterfaceOperationResponse) response.getActionResponse();
        System.out.println("Sonuç..." + addNIResponse.getResult());
        Assert.assertEquals(addNIResponse.getResult(), "Başarılı");
        client.stopConnection();
    }

}
