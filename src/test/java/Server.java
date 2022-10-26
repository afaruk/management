import clientApi.mbean.ManagementOperationsMBean;
import server.management.ManagementService;
import server.management.*;
import server.management.mbean.ManagementOperations;
import server.management.operations.AddNetworkInterfaceOpRequestHandler;
import server.management.operations.RequestDispatcherImpl;
import server.management.session.ManagementSessionService;
import server.management.session.ManagementSessionServiceImpl;
import server.user.UserRepository;
import server.user.auth.ManagementAuthenticator;

import java.io.IOException;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import javax.management.*;
import javax.management.remote.JMXAuthenticator;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.management.remote.rmi.RMIConnectorServer;
import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.rmi.ssl.SslRMIServerSocketFactory;

public class Server {

    public static void main(String[] args) throws Exception {
        int port = 9999;
        String configPath= Paths.get("src","test","java","config").toFile().getAbsolutePath();
        Server server = new Server(port, configPath);
        server.start();
    }

    private JMXConnectorServer server;

    public Server(int port, String configPath) throws Exception {
        setSystemEnvironments(port, configPath);

        var sessionService = new ManagementSessionServiceImpl();
        var userRepository = new UserRepository();
        ManagementService mngmntService = createManagementService(sessionService);
        server = createServer(port, mngmntService, sessionService, userRepository);
    }

    public void start() throws IOException {
        server.start();
        System.out.println("\nSunucu başlatılmıştır");
    }

    private ManagementService createManagementService(ManagementSessionService sessionService) {
        var requestDispatcher = new RequestDispatcherImpl();
        var addNetworkInterfaceOpRequestHandler = new AddNetworkInterfaceOpRequestHandler();
        requestDispatcher.registerHandler(addNetworkInterfaceOpRequestHandler);
        return new ManagementServiceImpl(sessionService, requestDispatcher);
    }

    private JMXConnectorServer createServer(int port, ManagementService mngmntService, ManagementSessionService sessionService, UserRepository userRepository) throws IOException, MalformedObjectNameException, InstanceAlreadyExistsException, NotCompliantMBeanException, MBeanRegistrationException {
        MBeanServer mbs = registerMBean(mngmntService);
        var managementAuthenticator = new ManagementAuthenticator(sessionService, userRepository);
        HashMap env = setJMXEnvironments(managementAuthenticator);
        JMXServiceURL url = new JMXServiceURL(
                "service:jmx:rmi:///jndi/rmi://localhost:"+ port +"/server");
        return JMXConnectorServerFactory.newJMXConnectorServer(url, env, mbs);
    }

    private HashMap setJMXEnvironments(JMXAuthenticator authenticator) {
        HashMap env = new HashMap();
        SslRMIClientSocketFactory csf = new SslRMIClientSocketFactory();
        SslRMIServerSocketFactory ssf = new SslRMIServerSocketFactory();
        env.put(RMIConnectorServer.RMI_CLIENT_SOCKET_FACTORY_ATTRIBUTE,csf);
        env.put(RMIConnectorServer.RMI_SERVER_SOCKET_FACTORY_ATTRIBUTE,ssf);
        env.put("jmx.remote.authenticator", authenticator);
        return env;
    }

    private MBeanServer registerMBean(ManagementService mngmntService) throws MalformedObjectNameException, InstanceAlreadyExistsException, MBeanRegistrationException, NotCompliantMBeanException {
        ManagementOperations managementOperations = new ManagementOperations(mngmntService);
        MBeanServer mbs = MBeanServerFactory.createMBeanServer();
        ObjectName objectName =
                new ObjectName("org.example.MyApplication:type="+ ManagementOperationsMBean.class.getName()+",name=Example");
        StandardMBean mbean = new StandardMBean(managementOperations, ManagementOperationsMBean.class);
        mbs.registerMBean(mbean, objectName);
        return mbs;
    }

    private void setSystemEnvironments(int port, String configPath) throws RemoteException {
        LocateRegistry.createRegistry(port);
        System.setProperty("javax.net.ssl.keyStore", Paths.get(configPath, "keystore").toString());
        System.setProperty("javax.net.ssl.keyStorePassword", "password");
    }
}
