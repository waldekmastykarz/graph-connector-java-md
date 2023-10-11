package connector;

import java.util.Properties;
import com.microsoft.graph.models.Request;
import com.microsoft.graph.requests.GraphServiceClient;

public class GraphService {
    private static GraphServiceClient<Request> client;

    public static GraphServiceClient<Request> getClient() {
        if (client == null) {
            final Properties properties = new Properties();
            properties.load(App.class.getResourceAsStream("oAuth.properties"));

            // client = GraphServiceClient.builder()
            //         .authenticationProvider(new AuthenticationProvider())
            //         .buildClient();
        }

        return client;
    }
}
