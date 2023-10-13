package connector;

import java.io.IOException;

import com.microsoft.graph.externalconnectors.models.ConnectionOperation;
import com.microsoft.graph.externalconnectors.models.ConnectionOperationStatus;

import okhttp3.Request;
import okhttp3.Response;

public class CompleteJobWithDelayHandler implements okhttp3.Interceptor {
    private int delayMs;

    public CompleteJobWithDelayHandler(int delayMs) {
        this.delayMs = delayMs;
    }

    @Override
    public Response intercept(Chain arg0) throws IOException {
        final Response response = arg0.proceed(arg0.request());

        final String location = response.header("Location");
        if (location != null) {
            if (location.indexOf("/operations/") < 0) {
                // not a job URL we should follow
                return response;
            }

            System.out.printf("Location: %s%n", location);
            System.out.printf("Waiting %dms before following location %s...%n", this.delayMs, location);
            try {
                Thread.sleep(this.delayMs);
            } catch (InterruptedException e) {
            }

            final Request newRequest = arg0.request()
                .newBuilder()
                .url(location)
                .method("GET", null)
                .build();

            return arg0.proceed(newRequest);
        }

        if (!response.isSuccessful()) {
            return response;
        }

        if (arg0.request().url().toString().indexOf("/operations/") < 0) {
            // not a job
            return response;
        }

        final String body = response.peekBody(Long.MAX_VALUE).string();
        final ConnectionOperation operation = ConnectionOperation.deserializeObject(body);

        if (operation.status == ConnectionOperationStatus.INPROGRESS) {
            System.out.printf("Waiting %dms before retrying %s...%n", this.delayMs, arg0.request().url().toString());
            try {
                Thread.sleep(this.delayMs);
            } catch (InterruptedException e) {
            }

            return this.intercept(arg0);
        }

        return response;
    }

}
