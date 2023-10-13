package connector;

import java.io.IOException;

import com.microsoft.graph.requests.UserCollectionPage;

public class App {
    public static void main(String[] args) throws IOException {
        final UserCollectionPage user = GraphService.getClient().users().buildRequest().get();
        System.out.println(user.getCurrentPage().get(0).displayName);
    }
}
