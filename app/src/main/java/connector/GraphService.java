package connector;

import java.io.IOException;
import java.util.Properties;

import com.azure.identity.ClientSecretCredential;
import com.azure.identity.ClientSecretCredentialBuilder;
import com.google.common.graph.GraphBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.Request;
import com.microsoft.graph.requests.GraphServiceClient;

public class GraphService {
    private static GraphServiceClient<Request> client;

    public static GraphServiceClient<Request> getClient() throws IOException {
        if (client == null) {
            final Properties properties = new Properties();
            properties.load(App.class.getResourceAsStream("application.properties"));

            final ClientSecretCredential credential = new ClientSecretCredentialBuilder()
                    .clientId(properties.getProperty("app:clientId"))
                    .clientSecret(properties.getProperty("app:clientSecret"))
                    .tenantId(properties.getProperty("app:tenantId"))
                    .build();
            final TokenCredentialAuthProvider authProvider = new TokenCredentialAuthProvider(graphUserScopes, _deviceCodeCredential);

            GraphServiceClient.builder().
        }

        return client;
    }
}
